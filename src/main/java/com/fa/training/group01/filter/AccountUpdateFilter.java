//package com.fa.training.group01.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.fa.training.group01.payload.ActiveAndRoleUser;
//import com.fa.training.group01.security.CustomUserDetails;
//import com.fa.training.group01.util.AuthenciationHelper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebFilter("/*")
//@Component
//public class AccountUpdateFilter extends GenericFilterBean {
//	@Autowired
//	private RestTemplate restTemplate;
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		System.out.println(restTemplate);
//		if (AuthenciationHelper.isAuthenticated()) {
//			CustomUserDetails userDetails = AuthenciationHelper.getCurrentUserDetail();
//			String url = UriComponentsBuilder.fromUriString("/active-role")
//					.queryParam("email", userDetails.getUsername()).toUriString();
//			ResponseEntity<String> textResponse = restTemplate.getForEntity(url, String.class);
//			if (StringUtils.hasText(textResponse.getBody())) {
//				httpResponse.sendRedirect(httpRequest.getContextPath());
//			}
//			ActiveAndRoleUser activeAndRoleUser = objectMapper.readValue(textResponse.getBody(),
//					ActiveAndRoleUser.class);
//			System.out.println(activeAndRoleUser);
//			if (!activeAndRoleUser.isActive()) {
//				new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, (Authentication) userDetails);
//				SecurityContextHolder.getContext().setAuthentication(null);
//				httpResponse.sendRedirect(httpRequest.getContextPath());
//			}
//			AuthenciationHelper.setAuthority(activeAndRoleUser.getRole());
//
//		}
//		chain.doFilter(request, response);
//
//	}
//
//}
