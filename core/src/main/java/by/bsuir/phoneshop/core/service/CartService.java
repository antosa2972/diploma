package by.bsuir.phoneshop.core.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import by.bsuir.phoneshop.core.models.Cart;

public interface CartService
{

	Cart getCart(final HttpSession httpSession);

	void addPhone(final Long phoneId, final Long quantity, final Cart cart) throws RuntimeException;

	void update(final Map<Long, Long> items, final Cart cart);

	void remove(final Long phoneId, final Cart cart);

	void deleteCart(final HttpSession httpSession);
}
