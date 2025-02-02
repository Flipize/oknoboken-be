package com.nilsson.oknoboken_be;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    @Value("${image.dir}")
    private String imageDirectory;
    @Value("${images.json}")
    private String imageJsonPath;
    private List<Image> images;

    @PostConstruct
    public void loadImages() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(imageJsonPath);

        if (!jsonFile.exists()) {
            throw new Exception("JSON file not found: " + imageJsonPath);
        }

        images = mapper.readValue(jsonFile, new TypeReference<List<Image>>() {});
        System.out.println("Loaded " + images.size() + " images.");
    }

    public Resource getImageAsResource(String filename) throws Exception {
        // Locate the file
        Path filePath = Paths.get(imageDirectory).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new Exception("File not found or not readable: " + filename);
        }
    }

    public String getImageContentType(String filename) throws Exception {
        Path filePath = Paths.get(imageDirectory).resolve(filename).normalize();
        String contentType = Files.probeContentType(filePath);

        if (contentType == null) {
            throw new Exception("Unable to determine content type for file: " + filename);
        }
        return contentType;
    }

    public List<Image> getAllImages() {
        return images;
    }

    public Image getImageByFilename(String filename) {
        return images.stream()
                .filter(image -> image.getFilename().equals(filename))
                .findFirst()
                .orElse(null);
    }
}
