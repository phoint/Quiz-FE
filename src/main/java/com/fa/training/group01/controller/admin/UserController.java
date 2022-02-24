package com.fa.training.group01.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.jpa.EntityPageResponse;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.UrlUtil;

@Controller("AdminUserController")
@RequestMapping(UrlUtil.AdminArea.PATH + UrlUtil.AdminArea.User.PATH)
public class UserController {
	@Autowired
	private IUserService userService;
	public static final int PAGE_SIZE = 5;

	@RequestMapping(value = UrlUtil.AdminArea.User.PathName.ACCOUNT_LIST, method = RequestMethod.GET)
	public ModelAndView userPage() {
		ModelAndView mav = new ModelAndView("admin/user-admin");
		EntityPageResponse<User> test = userService.findAll();
		
		return mav;
	}

}
