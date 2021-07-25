package com.gigabytes.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix="phnbook")
@Configuration
public class AppProperties {

	public AppProperties(Map<String, String> messages) {
		super();
		this.messages = messages;
	}

 	private Map<String, String> messages = new HashMap<>();

}
