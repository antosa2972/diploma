package by.bsuir.phoneshop.web.controller.pages.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.phoneshop.core.models.User;
import by.bsuir.phoneshop.core.exception.errors.ResponseErrors;
import by.bsuir.phoneshop.core.dto.UserDto;
import by.bsuir.phoneshop.core.service.UserService;
import by.bsuir.phoneshop.web.controller.constants.PhoneshopPages;

@Controller
@RequestMapping(value = "/admin/user-management")
public class UserManagementController
{
	@Resource
	private UserService userServiceImpl;

	private static final int LIMIT_ON_PAGE = 10;

	@GetMapping
	public String showUserManagementPage(final Model model, @RequestParam(name = "page", required = false) Integer page)
	{
		if (page == null)
		{
			page = 0;
		}
		int offset = page * LIMIT_ON_PAGE;

		final List<User> users = userServiceImpl.getUsersExceptAdmin(LIMIT_ON_PAGE, offset);
		model.addAttribute("users", users);

		return PhoneshopPages.UserPages.UserManagementPage;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update-status", consumes = "application/json")
	public ResponseEntity<?> updateUserStatus(final @RequestBody UserDto userDto)
	{
		int statusToUpdate = userDto.getStatus() == 0 ? 1 : 0;
		try
		{
			userServiceImpl.updateUserStatus(statusToUpdate, userDto.getUserName());
			return ResponseEntity.ok().build();
		}
		catch (Exception e)
		{
			final ResponseErrors errors = new ResponseErrors(e.getMessage());
			return ResponseEntity.badRequest().body(errors);
		}
	}
}
