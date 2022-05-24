package by.bsuir.phoneshop.core.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.dao.StockDao;
import by.bsuir.phoneshop.core.models.Cart;
import by.bsuir.phoneshop.core.models.CartItem;
import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.models.Stock;
import by.bsuir.phoneshop.core.service.CartService;

@Service
public class HttpSessionCartService implements CartService
{
	public static final String CART_SESSION_ATTR = "cart";

	@Resource
	private PhoneDao jdbcPhoneDao;
	@Resource
	private StockDao jdbcStockDao;

	@Override
	@Transactional(readOnly = true)
	public synchronized Cart getCart(final HttpSession httpSession)
	{
		Cart cart = (Cart) httpSession.getAttribute(CART_SESSION_ATTR);
		if (cart == null)
		{
			cart = new Cart();
			httpSession.setAttribute(CART_SESSION_ATTR, cart);
		}
		return cart;
	}

	@Override
	@Transactional(rollbackFor = DataAccessException.class)
	public synchronized void addPhone(final Long phoneId, final Long quantity, final Cart cart) throws RuntimeException
	{
		final Optional<Phone> optionalPhone = jdbcPhoneDao.get(phoneId);
		final Optional<Stock> optionalStock = jdbcStockDao.get(phoneId);

		if (optionalPhone.isPresent() && optionalStock.isPresent())
		{
			final Stock stock = optionalStock.get();
			final Phone phone = optionalPhone.get();
			if (stock.getStock() - stock.getReserved() >= quantity)
			{
				addToCart(quantity, stock, phone, cart);
			}
			else
			{
				throw new RuntimeException();
			}
		}
		else
		{
			throw new IllegalArgumentException("No stock or phone in data base");
		}
	}

	private void addToCart(final Long quantity, final Stock stock, final Phone phone, Cart cart)
	{
		jdbcStockDao.update(phone.getId(), stock.getStock().longValue(), stock.getReserved() + quantity, false);
		final Optional<CartItem> cartItem = findCartItem(phone.getId(), cart);
		if (cartItem.isPresent())
		{
			final CartItem existingCartItem = cartItem.get();
			existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
		}
		else
		{
			final BigDecimal price = phone.getActualPrice().multiply(BigDecimal.valueOf(quantity));
			cart.getCartItems().add(new CartItem(phone, quantity, price));
		}
		calculateCart(cart);
	}

	@Override
	@Transactional(rollbackFor = DataAccessException.class)
	public synchronized void update(final Map<Long, Long> items, final Cart cart)
	{
		items.keySet().stream()
					 .map(phoneId -> findCartItem(phoneId, cart))
					 .filter(Optional::isPresent)
					 .map(Optional::get)
					 .forEach(cartItem ->
					 {
						 final Long phoneId = cartItem.getPhone().getId();
						 final Long quantity = items.get(cartItem.getPhone().getId());
						 final Long quantityDifference = quantity - cartItem.getQuantity();
						 if (checkQuantity(phoneId, quantityDifference))
						 {
							 cartItem.setQuantity(cartItem.getQuantity() + quantityDifference);
						 }
					 });
		calculateCart(cart);
	}

	private boolean checkQuantity(final Long phoneId, final Long quantityDifference)
	{
		final Optional<Stock> optionalStock = jdbcStockDao.get(phoneId);
		if (optionalStock.isPresent())
		{
			final Stock stock = optionalStock.get();
			if (stock.getStock() - stock.getReserved() >= quantityDifference)
			{
				jdbcStockDao.update(phoneId, stock.getStock().longValue(),
							 stock.getReserved() + quantityDifference, false);
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor = DataAccessException.class)
	public synchronized void remove(final Long phoneId, final Cart cart)
	{
		final Optional<CartItem> optionalCartItem = findCartItem(phoneId, cart);
		if (optionalCartItem.isPresent())
		{
			cart.getCartItems().remove(optionalCartItem.get());
		}
		else
		{
			throw new IllegalArgumentException(phoneId.toString());
		}

		final Optional<Stock> optionalStock = jdbcStockDao.get(phoneId);
		if (optionalStock.isPresent() && optionalCartItem.isPresent())
		{
			final Stock stock = optionalStock.get();
			final CartItem cartItem = optionalCartItem.get();
			jdbcStockDao.update(phoneId, stock.getStock() + cartItem.getQuantity(),
						 stock.getReserved() - cartItem.getQuantity(), false);
		}
		calculateCart(cart);
	}

	@Override
	public void deleteCart(final HttpSession httpSession)
	{
		httpSession.removeAttribute("cart");
	}


	private Optional<CartItem> findCartItem(final Long phoneId, final Cart cart)
	{
		return cart.getCartItems().stream()
					 .filter(cartItem -> cartItem.getPhone().getId().equals(phoneId))
					 .findFirst();
	}

	private void calculateCart(final Cart cart)
	{
		final Long totalQuantity = cart.getCartItems().stream()
					 .mapToLong(CartItem::getQuantity)
					 .sum();
		cart.setTotalQuantity(totalQuantity);
		final BigDecimal totalCost = cart.getCartItems().stream()
					 .map(cartItem -> cartItem.getPhone().getActualPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
					 .reduce(BigDecimal.ZERO, BigDecimal::add);

		cart.setTotalCost(totalCost);
	}
}
