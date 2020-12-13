package com.holidu.interview.assignment.context;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.holidu.interview.assignment.util.ObjectMapperFactory;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

import java.util.List;

@TestConfiguration
@EnableWebMvc
public class TestMvcConfig implements WebMvcConfigurer {
	@Override
	public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
		converters.add(springBootAdminMappingJackson2HttpMessageConverter());
	}

	private MappingJackson2HttpMessageConverter springBootAdminMappingJackson2HttpMessageConverter() {
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(ObjectMapperFactory.jsonObjectMapper(false));
		return jsonConverter;
	}

	@Bean
	public ErrorAttributes errorAttributes() {
		return mock(ErrorAttributes.class);
	}
}
