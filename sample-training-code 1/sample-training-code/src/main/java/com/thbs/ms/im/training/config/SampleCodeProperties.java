package com.thbs.ms.im.training.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Configuration
@ConfigurationProperties("downstream")
@Getter
@Setter
public class SampleCodeProperties {
	
	private static final Duration DEFAULT_READ_TIMEOUT_SECONDS = Duration.ofSeconds(25);
	
	private static final Duration DEFAULT_CONNECTION_TIMEOUT_SECONDS = Duration.ofSeconds(5);

	private Sample sample;
	
	@Getter
	@Setter
	public static class Sample{
		
		private String url;
		
		@DurationUnit(ChronoUnit.SECONDS)
		private Duration readTimeoutSecounds = DEFAULT_READ_TIMEOUT_SECONDS;
		
		@DurationUnit(ChronoUnit.SECONDS)
        private Duration connectionTimeoutSeconds = DEFAULT_CONNECTION_TIMEOUT_SECONDS;

		
		
	}
}
