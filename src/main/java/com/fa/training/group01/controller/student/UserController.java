package com.fa.training.group01.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.model.CurrentUser;
import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.security.CustomUserDetails;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.UrlUtil;

@Controller()
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = UrlUtil.StudentArea.User.PathName.PROFILE, method = RequestMethod.GET)
	public ModelAndView myAccountPage(@CurrentUser CustomUserDetails currentUser) {
		User user = userService.getByEmail(currentUser.getUsername());
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", user);
		mav.setViewName("student/my-profile");
		return mav;
	}

	@RequestMapping(value = UrlUtil.StudentArea.User.PathName.UPDATE_PASSWORD, method = RequestMethod.GET)
	public ModelAndView updatePasswordPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/update-password");
		return mav;
	}

	@RequestMapping(value = UrlUtil.StudentArea.User.PathName.UPDATE_PASSWORD, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		ResponseEntity<String> responseEntity = userService.updatePassword(updatePasswordRequest);
		if (responseEntity.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).headers(headers)
					.body(responseEntity.getBody());
		}
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return ResponseEntity.status(HttpStatus.OK).body(responseEntity.getBody());
		}
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
	}
}
