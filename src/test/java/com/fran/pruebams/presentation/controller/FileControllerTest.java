package com.fran.pruebams.presentation.controller;

import com.fran.pruebams.application.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    private MockMultipartFile file;

    @BeforeEach
    void setUp() {
        file = new MockMultipartFile(
                "file",
                "hello.txt",
                "text/plain",
                "Hello, World!".getBytes());
    }

    @Test
    public void copyFile_FileExists_ReturnsConflict() {
        when(fileService.fileExists(file)).thenReturn(true);

        ResponseEntity<?> response = fileController.copyFile(file);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void copyFile_NoException_ReturnsOK() throws Exception {
        when(fileService.fileExists(file)).thenReturn(false);

        ResponseEntity<?> response = fileController.copyFile(file);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
