package com.thbs.ms.im.training.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thbs.ms.im.training.entity.BaseRequest;
import com.thbs.ms.im.training.entity.ResponseBean;
import com.thbs.ms.im.training.entity.SampleRequest;
import com.thbs.ms.im.training.entity.SampleResponse;
import com.thbs.ms.im.training.service.SampleCodeServiceImpl;
import com.thbs.ms.im.training.entity.EmployeeRequest;
import com.thbs.ms.im.training.mapper.*;
@RestController
@RequestMapping("/base-path")
public class SampleCodeController { 

	
	@Autowired
	private SampleCodeServiceImpl sampelCodeServiceImpl;
	

	//Controller class
	@PostMapping(value="/resource-path", produces={MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> sampleCodeService(
            @RequestHeader(value = "Tracking-Header",required = true)String trackingHeader,
            @RequestHeader(value = "Content-Type",required = false)String contentType,

            @RequestBody Map<String,String> requestBody){
		
		String employeename = requestBody.get("employeename");
        String employeeid = requestBody.get("employeeid");
        SampleRequest sampleRequest = SampleRequestMapper.mapRequest(employeename, employeeid);
		System.out.println("We are in Controller");
		System.out.println("tracking header from postman--"+trackingHeader);
		System.out.println("employee name - "+sampleRequest.getEname());

		
		BaseRequest request = new BaseRequest(); 
		request.setTrackingHeader(trackingHeader);
		request.setContentType(contentType);
		
		System.out.println("tracking Header -- "+request.getTrackingHeader());
		System.out.println("Content-Type---" +request.getContentType());
		
		
		ResponseBean<SampleResponse> response = sampelCodeServiceImpl.createOrder(sampleRequest,request);

		if(response.isSuccess()) {
			 return new ResponseEntity<>(response.getData(),HttpStatus.OK);
		} else 
			return null;
		
	}
}










