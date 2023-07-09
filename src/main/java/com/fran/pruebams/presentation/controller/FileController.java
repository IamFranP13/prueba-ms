package com.fran.pruebams.presentation.controller;

import com.fran.pruebams.application.exceptions.FileAlreadyExistsException;
import com.fran.pruebams.application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;


@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/api/copy")
    public ResponseEntity<?> copyFile(@RequestParam("file") MultipartFile file) {
        if (fileService.fileExists(file)) {
            return new ResponseEntity<>(Map.of("error", "File already exists"), HttpStatus.CONFLICT);
        }

        try {
            fileService.saveFile(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
