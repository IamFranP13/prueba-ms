package com.fran.pruebams.application.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public boolean fileExists(MultipartFile file) {

        Path path = Paths.get("files/" + file.getOriginalFilename());
        return Files.exists(path);
    }

    @Async
    public void saveFile(MultipartFile file) throws com.fran.pruebams.application.exceptions.FileAlreadyExistsException {
        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);

            String directory = "files";
            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Thread writerThread = new Thread(() -> {
                try (in) {
                    Path path = Paths.get("files/" + file.getOriginalFilename());
                    if (Files.exists(path)) {
                        throw new com.fran.pruebams.application.exceptions.FileAlreadyExistsException("File already exists");
                    }
                    Files.copy(in, path);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            });

            Thread readerThread = new Thread(() -> {
                try {
                    IOUtils.copy(file.getInputStream(), out);
                } catch (IOException e) {

                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            writerThread.start();
            readerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
