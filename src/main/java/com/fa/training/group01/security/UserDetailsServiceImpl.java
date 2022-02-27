package com.fa.training.group01.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.LoginRequest;
import com.fa.training.group01.service.IAuthService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.RestTemplateUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private IAuthService authService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			String authProvider = request.getParameter("authProvider");
			String accessToken = request.getParameter("accessToken");
			String password = request.getParameter("password");
			LoginRequest loginRequest = new LoginRequest(username, password, authProvider, accessToken);
			AuthenciationToken authenciationToken = authService.getAuthToken(loginRequest);
			RestTemplateUtil.addBeaerAuth(restTemplate, authenciationToken);
			User user = userService.getByAuthenciation(authenciationToken);
			return new CustomUserDetails(user, authenciationToken);
		} catch (Exception e) {
			System.err.println(e);
			throw new UsernameNotFoundException("username not found");
		}

	}

}
