package com.fa.training.group01.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.training.group01.payload.ActiveAndRoleUser;
import com.fa.training.group01.security.CustomUserDetails;
import com.fa.training.group01.util.AuthenciationHelper;
import com.fa.training.group01.util.RestTemplateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccountInterceptor implements HandlerInterceptor {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (AuthenciationHelper.isLogged()) {
			CustomUserDetails userDetails = AuthenciationHelper.getCurrentUserDetail();
			String url = UriComponentsBuilder.fromUriString("/active-role")
					.queryParam("email", userDetails.getUsername()).toUriString();
			ResponseEntity<String> textResponse = restTemplate.getForEntity(url, String.class);
			if (!StringUtils.hasText(textResponse.getBody())) {
				logout(request,response);
				response.sendRedirect(request.getContextPath());
				return false;
			}
			ActiveAndRoleUser activeAndRoleUser = objectMapper.readValue(textResponse.getBody(),
					ActiveAndRoleUser.class);
			if (!activeAndRoleUser.isActive()) {
				logout(request,response);
				response.sendRedirect(request.getContextPath());
				return false;
			}
			AuthenciationHelper.setAuthority(activeAndRoleUser.getRole());
		}
		return true;
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		AuthenciationHelper.logout(request, response);
		RestTemplateUtil.removeBearerAuth(restTemplate);
	}
}
