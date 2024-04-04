package com.ryhan.soapclient.config;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.stereotype.Component;

import com.ryhan.soapclient.entity.UpdateStudentDetailsRequest;
import com.ryhan.soapclient.entity.UpdateStudentDetailsResponse;

public class Client extends WebServiceGatewaySupport {

	public UpdateStudentDetailsResponse updateStudent(UpdateStudentDetailsRequest request) {
    	HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
    	messageSender.setConnectionTimeout(5000); // Set connection timeout if needed
        messageSender.setReadTimeout(5000); // Set read timeout if needed
        getWebServiceTemplate().setMessageSender(messageSender);

        UpdateStudentDetailsResponse response = (UpdateStudentDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

		return response;
		
	}
}