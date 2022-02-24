package com.fa.training.group01.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.config.HypermediaRestTemplateConfigurer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.fa.training.group01.util.API;
import com.fasterxml.jackson.databind.ObjectMapper;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@EnableHypermediaSupport(type = HypermediaType.HAL)
@Configuration
@EnableWebMvc
@ComponentScan("com.fa.training.group01")
@PropertySource({ "classpath:application.properties" })
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ResponseErrorHandler responseErrorHandler;

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		// SpringResourceTemplateResolver automatically integrates with Spring's own
		// resource resolution infrastructure, which is highly recommended.
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		// HTML is the default value, added here for the sake of clarity.
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		// Template cache is true by default. Set to false if you want
		// templates to be automatically updated when modified.
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		// SpringTemplateEngine automatically applies SpringStandardDialect and
		// enables Spring's own MessageSource message resolution mechanisms.
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		// Enabling the SpringEL compiler with Spring 4.2.4 or newer can
		// speed up execution in most scenarios, but might be incompatible
		// with specific cases when expressions in one template are reused
		// across different data types, so this flag is "false" by default
		// for safer backwards compatibility.
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	// define bean for RestTemplate ... this is used to make client REST calls
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// SET Default URL
		DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(API.HOST);
		restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);

		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		// restTemplate.getInterceptors().add(loggingRequestInterceptor);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		restTemplate.getMessageConverters().add(converter);

		restTemplate.setErrorHandler(responseErrorHandler);

		return restTemplate;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public HttpHeaders getHeader() {
		return new HttpHeaders();
	}

	/**
	 * Use the {@link HypermediaRestTemplateConfigurer} to configure a
	 * {@link RestTemplate}.
	 */
	@Bean
	@Autowired
	RestTemplate hypermediaRestTemplate(HypermediaRestTemplateConfigurer configurer) {
		RestTemplate template = configurer.registerHypermediaTypes(new RestTemplate());
		template.setErrorHandler(responseErrorHandler);
		return template;
	}

	// add resource handler for loading css, images, etc

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
