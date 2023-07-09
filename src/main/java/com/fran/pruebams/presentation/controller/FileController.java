package com.fran.pruebams.presentation.controller;

import com.fran.pruebams.application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/api/copy")
    public ResponseEntity<?> copyFile(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
