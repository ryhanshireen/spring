package com.ryhan.soapmicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryhan.soapclient.config.Client;
import com.ryhan.soapclient.entity.UpdateStudentDetailsRequest;
import com.ryhan.soapclient.entity.UpdateStudentDetailsResponse;
import com.ryhan.soapmicroservice.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String updateStudent(String studentId, String studentName) {
        UpdateStudentDetailsRequest request = new UpdateStudentDetailsRequest();
        request.setStudentID(studentId);
        request.setStudentName(studentName);

        UpdateStudentDetailsResponse response = studentRepository.UpdateStudent(request);
        if (response != null) {
            return response.getStatus();
        } else {
            return "Error: Null response received.";
        }
    }
}


