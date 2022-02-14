package com.fa.training.group01.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.LoginRequest;
import com.fa.training.group01.security.AuthenciationToken;
import com.fa.training.group01.security.CustomUserDetails;
import com.fa.training.group01.service.IAuthService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.RestTemplateUtil;

@Controller
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IAuthService authService;
	@Autowired
	private IUserService userService;

	@Autowired

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "student/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@ModelAttribute LoginRequest loginRequest) {
		try {
			AuthenciationToken authenciationToken = authService.getAuthToken(loginRequest);
			RestTemplateUtil.addBeaerAuth(restTemplate, authenciationToken);
			User user = userService.getByAuthenciation(authenciationToken);
			CustomUserDetails customUserDetails = new CustomUserDetails(user, authenciationToken);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					customUserDetails, customUserDetails.getPassword(), customUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			return "success";
		} catch (Exception e) {
			System.err.println(e);
			return "failed";
		}

	}
}
