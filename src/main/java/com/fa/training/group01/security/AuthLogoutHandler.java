package com.fa.training.group01.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.util.RestTemplateUtil;

@Component
public class AuthLogoutHandler implements LogoutHandler {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	private RestTemplate template; 
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		RestTemplateUtil.removeBearerAuth(restTemplate);
		RestTemplateUtil.removeBearerAuth(template);
		restTemplate.getInterceptors().forEach(System.out::println);
	}

}
