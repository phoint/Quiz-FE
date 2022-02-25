package com.fa.training.group01.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.payload.LoginRequest;
import com.fa.training.group01.security.AuthenciationToken;
import com.fa.training.group01.security.CustomUserDetails;
import com.fa.training.group01.service.IAuthService;
import com.fa.training.group01.service.IQuizService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.RestTemplateUtil;
import com.fa.training.group01.util.UrlUtil;

@Controller
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	@Qualifier(value = "hypermediaRestTemplate")
	private RestTemplate template;
	
	@Autowired
	private IAuthService authService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IQuizService quizService;
	
	@ModelAttribute("quizzes")
	public List<Quiz> populateQuiz() {
		return quizService.findAll();
	}
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "student/index";
	}

	@RequestMapping(value = UrlUtil.Public.PathName.LOGIN, method = RequestMethod.POST)
	@ResponseBody
	public String login(@ModelAttribute LoginRequest loginRequest) {
		try {
			AuthenciationToken authenciationToken = authService.getAuthToken(loginRequest);
			RestTemplateUtil.addBeaerAuth(restTemplate, authenciationToken);
			RestTemplateUtil.addBeaerAuth(template, authenciationToken);
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

	@RequestMapping(value = UrlUtil.Public.PathName.RESET_PASSWORD, method = RequestMethod.GET)
	public ModelAndView resetPasswordPage(@RequestParam(value = "token", required = false) String token) {
		ModelAndView mav = new ModelAndView();
		if (!userService.existsResetPasswordToken(token)) {
			mav.setViewName(UrlUtil.REDIRECT_PREFIX + "/");
			return mav;
		}
		mav.setViewName("student/reset-password");
		return mav;
	}
	
	@RequestMapping(value="/home")
	public String showHome() {
		
		return "student/home";
	}

}
