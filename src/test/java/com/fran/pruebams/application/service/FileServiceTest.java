package com.fran.pruebams.application.service;

import com.fran.pruebams.application.exceptions.FileAlreadyExistsException;
import org.springframework.mock.web.MockMultipartFile;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileServiceTest {

    @Test
    public void testSaveFile() throws IOException, FileAlreadyExistsException, InterruptedException {

        FileService fileService = new FileService();
        File tempFile = File.createTempFile("testfile", ".txt");

        try (InputStream is = new FileInputStream(tempFile)) {
            MultipartFile multipartFile = new MockMultipartFile("testfile", "testfile.txt", "text/plain", is);

            fileService.saveFile(multipartFile);

            Thread.sleep(2000);

            assertTrue(fileService.fileExists(multipartFile));
        }
    }

    @Test
    public void testFileExists() throws IOException, FileAlreadyExistsException, InterruptedException {

        FileService fileService = new FileService();

        File tempFile = File.createTempFile("testfile", ".txt");

        try (InputStream is = new FileInputStream(tempFile)) {
            MultipartFile multipartFile = new MockMultipartFile("testfile", "testfile.txt", "text/plain", is);

            fileService.saveFile(multipartFile);

            Thread.sleep(2000);

            assertTrue(fileService.fileExists(multipartFile));
        }
    }
}
