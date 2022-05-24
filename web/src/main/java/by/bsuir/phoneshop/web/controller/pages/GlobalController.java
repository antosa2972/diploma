package by.bsuir.phoneshop.web.controller.pages;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@RequestMapping("/error")
public class GlobalController
{
	private static final String ADMIN_MAIL="mailto:antosa2972@gmail.com";

	@ExceptionHandler(NoHandlerFoundException.class)
	public String exception404(final HttpServletRequest httpRequest, final HttpServletResponse httpServletResponse, final Model model)
	{
		model.addAttribute("adminMail", ADMIN_MAIL);
		model.addAttribute("status", "404");
		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	@GetMapping("/access-denied")
	public String accessDenied(final HttpServletRequest httpRequest, final HttpServletResponse httpServletResponse, final Model model)
	{
		model.addAttribute("adminMail", ADMIN_MAIL);
		model.addAttribute("status", "403");
		return "error";
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String internalServerError(final HttpServletRequest httpRequest, final HttpServletResponse httpServletResponse, final Model model)
	{
		model.addAttribute("adminMail", ADMIN_MAIL);
		model.addAttribute("status", "500");
		return "error";
	}
}