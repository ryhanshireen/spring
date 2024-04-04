package com.thbs.ms.im.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thbs.ms.im.training.entity.BaseRequest;
import com.thbs.ms.im.training.entity.ResponseBean;
import com.thbs.ms.im.training.entity.SampleRequest;
import com.thbs.ms.im.training.entity.SampleResponse;
import com.thbs.ms.im.training.repository.SampleCodeRepositoryImpl;

@Service
public class SampleCodeServiceImpl {
	
	@Autowired SampleCodeRepositoryImpl sampleCodeRepositoryImpl;

	public ResponseBean<SampleResponse> createOrder(SampleRequest sampleRequest, BaseRequest request) {
		
		System.out.println("In Service class");
		
		System.out.println("Headers from controller --"+request.getContentType());
		
		ResponseBean<SampleResponse> response = sampleCodeRepositoryImpl.createProductOrder(sampleRequest,request);
		
		
		if(response.isSuccess()) {
			return ResponseBean.of(response.getData());
		}else {
			 return ResponseBean.errorRes("400","Bad Request");
		}
	}

}
