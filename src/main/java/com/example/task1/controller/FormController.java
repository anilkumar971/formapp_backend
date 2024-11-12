package com.example.task1.controller;



import com.example.task1.entity.Form;
import com.example.task1.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/form")

@CrossOrigin(origins = "http://localhost:4200")
public class FormController {

    @Autowired
    private FormService formService;

    // Endpoint to handle form submission with file uploads
    @PostMapping("/submit")
    public ResponseEntity<Form> submitForm(
            @RequestParam("gender") Integer gender,
            @RequestParam("age") Integer age,
            @RequestParam(value = "selfie", required = false) MultipartFile selfie,
            @RequestParam(value = "audio", required = false) MultipartFile audio,
            @RequestParam(value = "gpsCoordinates", required = false) String gpsCoordinates) {

        try {
            Form savedForm = formService.saveFormResponse(gender, age, selfie, audio, gpsCoordinates);
            return ResponseEntity.ok(savedForm);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
