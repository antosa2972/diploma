package by.bsuir.phoneshop.core.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import by.bsuir.phoneshop.core.beans.Cart;
import by.bsuir.phoneshop.core.exception.OutOfStockException;

public interface CartService
{

	Cart getCart(final HttpSession httpSession);

	void addPhone(final Long phoneId, final Long quantity, final Cart cart) throws OutOfStockException, IllegalArgumentException;

	void update(final Map<Long, Long> items, final Cart cart);

	void remove(final Long phoneId, final Cart cart);

	void deleteCart(final HttpSession httpSession);
}
