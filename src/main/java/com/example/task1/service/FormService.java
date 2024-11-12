package com.example.task1.service;



import com.example.task1.entity.Form;
import com.example.task1.repo.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    // Define the directory to save uploaded files
    private static final String UPLOAD_DIR = "uploads/";

    public Form saveFormResponse(Integer gender, Integer age, MultipartFile selfie, MultipartFile audio, String gpsCoordinates) throws IOException {
        Form form = new Form();
        form.setGender(gender);
        form.setAge(age);
        form.setGpsCoordinates(gpsCoordinates);
        form.setSubmitTime(LocalDateTime.now());

        // Ensure the upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save selfie file and set file path
        if (selfie != null && !selfie.isEmpty()) {
            String selfieFileName = UUID.randomUUID() + "_" + selfie.getOriginalFilename();
            String selfieFilePath = UPLOAD_DIR + selfieFileName;
            Files.write(Paths.get(selfieFilePath), selfie.getBytes());
            form.setSelfiePath(selfieFilePath);
        }

        // Save audio file and set file path
        if (audio != null && !audio.isEmpty()) {
            String audioFileName = UUID.randomUUID() + "_" + audio.getOriginalFilename();
            String audioFilePath = UPLOAD_DIR + audioFileName;
            Files.write(Paths.get(audioFilePath), audio.getBytes());
            form.setAudioPath(audioFilePath);
        }

        // Save form data to the database
        return formRepository.save(form);
    }
}
