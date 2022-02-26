package com.fa.training.group01.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.UpdateUserRequest;
import com.fa.training.group01.payload.UserPageRequest;
import com.fa.training.group01.payload.jpa.EntityPageResponse;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.UrlUtil;

@Controller("AdminUserController")
@RequestMapping(UrlUtil.AdminArea.PATH + UrlUtil.AdminArea.User.PATH)
public class UserController {
	@Autowired
	private IUserService userService;
	public static final int PAGE_SIZE = 2;

	@RequestMapping(value = UrlUtil.AdminArea.User.PathName.ACCOUNT_LIST, method = RequestMethod.GET)
	public ModelAndView userPage(UserPageRequest userPageRequest) {
		ModelAndView mav = new ModelAndView("admin/user-admin");
		setRequestParamValues(userPageRequest);
		EntityPageResponse<User> page = userService.findAll(userPageRequest, PAGE_SIZE);
		String url = UriComponentsBuilder
				.fromUriString(UrlUtil.AdminArea.PATH + UrlUtil.AdminArea.User.PATH
						+ UrlUtil.AdminArea.User.PathName.ACCOUNT_LIST)
				.queryParam(UserPageRequest.Fields.email, userPageRequest.getEmail())
				.queryParam(UserPageRequest.Fields.sortBy, userPageRequest.getSortBy()).toUriString();
		mav.addObject("page", page);
		mav.addObject("url", url);
		return mav;
	}

	@RequestMapping(value = UrlUtil.AdminArea.User.PathName.GET_USER, method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id") Integer id) {
		User user = userService.findOneByID(id);
		if (user != null) {
			user.setPassword("");
		}
		return user;
	}

	@RequestMapping(value = UrlUtil.AdminArea.User.PathName.UPDATE_USER, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity update(@RequestBody UpdateUserRequest updateUserRequest) {
		ResponseEntity<String> responseEntity = userService.updateUser(updateUserRequest);
		if (responseEntity == null) {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
		}
		return responseEntity;
	}

	private void setRequestParamValues(UserPageRequest userPageRequest) {
		int pageIndex = Optional.ofNullable(userPageRequest.getPageIndex()).orElse(1);
		String email = Optional.ofNullable(userPageRequest.getEmail()).orElse("");
		String sortBy = Optional.ofNullable(userPageRequest.getSortBy()).orElse("");
		userPageRequest.setEmail(email);
		userPageRequest.setPageIndex(pageIndex);
		userPageRequest.setSortBy(sortBy);
	}

}
