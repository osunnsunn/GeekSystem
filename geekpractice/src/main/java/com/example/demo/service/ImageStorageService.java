package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService {

    private final String UPLOAD_DIR = "uploads/";

    public String storeImage(MultipartFile imageFile) {

        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("画像の保存に失敗しました", e);
        }

        return fileName;
    }
}