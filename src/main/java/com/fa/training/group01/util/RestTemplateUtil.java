package com.fa.training.group01.util;

import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.interceptor.BearerAuthRequestInterceptor;
import com.fa.training.group01.security.AuthenciationToken;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestTemplateUtil {
	public static void addBeaerAuth(RestTemplate restTemplate, AuthenciationToken authenciationToken) {
		BearerAuthRequestInterceptor bearerAuthRequestInterceptor = null;
		for (ClientHttpRequestInterceptor interceptor : restTemplate.getInterceptors()) {
			if (interceptor instanceof BearerAuthRequestInterceptor) {
				bearerAuthRequestInterceptor = (BearerAuthRequestInterceptor) interceptor;
			}
		}
		if (bearerAuthRequestInterceptor == null) {
			bearerAuthRequestInterceptor = new BearerAuthRequestInterceptor();
			restTemplate.getInterceptors().add(bearerAuthRequestInterceptor);
		}
		bearerAuthRequestInterceptor.setToken(authenciationToken.getAccessToken());
	}

	public static void removeBearerAuth(RestTemplate restTemplate) {
		restTemplate.getInterceptors().removeIf(interceptor -> interceptor instanceof BearerAuthRequestInterceptor);
	}

}
