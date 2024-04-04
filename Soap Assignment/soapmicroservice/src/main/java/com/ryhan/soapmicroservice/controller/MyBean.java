package com.ryhan.soapmicroservice.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ryhan.soapclient.config.Client;

@Configuration
public class MyBean {

	@Bean
	public Client client() {
		return new Client();
	}
}
