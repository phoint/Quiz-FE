package com.fa.training.group01.interceptor;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BearerAuthRequestInterceptor implements ClientHttpRequestInterceptor {
	private String token;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		if (request.getHeaders().get(HttpHeaders.AUTHORIZATION) == null)
			request.getHeaders().setBearerAuth(token);
		return execution.execute(request, body);
	}

}
