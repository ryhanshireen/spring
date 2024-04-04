package com.ryhan.soapmicroservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ryhan.soapclient.config.Client;
import com.ryhan.soapclient.entity.UpdateStudentDetailsRequest;
import com.ryhan.soapclient.entity.UpdateStudentDetailsResponse;

@Repository
public class StudentRepository {

    @Autowired
    private Client client;
    
    
    public UpdateStudentDetailsResponse UpdateStudent(UpdateStudentDetailsRequest request) {
        return client.updateStudent(request);
    }
}