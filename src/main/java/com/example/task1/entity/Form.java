package com.example.task1.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gender; // Q1: Gender choice ID
    private Integer age;     // Q2: Age input
    private String selfiePath;   // Q3: Selfie image file path
    private String audioPath;    // Audio file path
    private String gpsCoordinates; // GPS coordinates (latitude, longitude)
    private LocalDateTime submitTime; // Submission time

    // Getters and Setters
}
