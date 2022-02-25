package com.fa.training.group01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.security.AuthLogoutHandler;
import com.fa.training.group01.security.RestAuthenticationFailureHandler;
import com.fa.training.group01.security.RestAuthenticationSuccessHandler;
import com.fa.training.group01.util.UrlUtil;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final String[] PUBLIC_MATCHERS = { UrlUtil.Public.PathName.HOME, UrlUtil.Public.PathName.LOGIN,
			UrlUtil.Public.PathName.RESET_PASSWORD, "/home" };

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthLogoutHandler authLogoutHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().antMatchers("/user/**")
				.hasAnyRole(Role.RoleName.STUDENT).antMatchers(Role.RoleName.ADMIN).hasAnyRole(Role.ADMIN.getOnlyName())
				.anyRequest().authenticated().and().formLogin().disable().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.addLogoutHandler(authLogoutHandler).deleteCookies("remember-me", "JSESSIONID").permitAll().and()
				.rememberMe().key("uniqueAndSecret").and().csrf().disable().cors();

//		http.authorizeRequests().anyRequest().permitAll().and().formLogin().disable().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
//				.deleteCookies("remember-me", "JSESSIONID").permitAll().and().rememberMe().key("uniqueAndSecret").and()
//				.csrf().disable().cors();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

}
