package com.ryhan.soapmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ryhan.soapmicroservice.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable String studentId, 
            @RequestBody String studentName) {
        String status = studentService.updateStudent(studentId, studentName);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}