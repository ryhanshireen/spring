package com.thbs.ms.im.training.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.thbs.ms.im.training.config.SampleCodeProperties;
import com.thbs.ms.im.training.entity.BaseRequest;
import com.thbs.ms.im.training.entity.ResponseBean;
import com.thbs.ms.im.training.entity.SampleRequest;
import com.thbs.ms.im.training.entity.SampleResponse;

@Repository
public class SampleCodeRepositoryImpl {
	
	
	private SampleCodeProperties properties;
	
	public SampleCodeRepositoryImpl(SampleCodeProperties properties) {
		super();
		
		this.properties = properties;
		
	}

	@Autowired
	RestTemplate restTemplate;

	public ResponseBean<SampleResponse> createProductOrder(SampleRequest sampleReq, BaseRequest request) {
		
		System.out.println("In Repository");
		
		HttpHeaders requestHeaders = new HttpHeaders(); 
	
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<?> orderRequest = new HttpEntity<>(sampleReq,requestHeaders);
		//System.out.println("headers sent to downstream--"+orderRequest.getHeaders().getContentType());
		
		//System.out.println("properties.getSample().getUrl()--"+properties.getSample().getUrl());
		
		String url = "http://localhost:9090/sampleTrainingURL";
		
		ResponseEntity<SampleResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,orderRequest,SampleResponse.class);
//System.out.println("url--"+url);
		//System.out.println("responseEntity.getBody(--"+responseEntity.getBody().getDate());
		if(responseEntity.status(200) != null) {
			System.out.println("yess--"+url);

			return ResponseBean.of(responseEntity.getBody());
			}
		return null;

		}

}



















