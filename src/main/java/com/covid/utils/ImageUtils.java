package com.covid.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageUtils {

    @Value("${fileUpload}")
    private String pathDir;

    public String save(MultipartFile file) {

        String filename = UUID.randomUUID()+ file.getOriginalFilename();
        Path filepath = Paths.get(pathDir, filename);
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
