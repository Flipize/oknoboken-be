package com.nilsson.oknoboken_be;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.util.List;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "info/all")
    public List<Image> listImages() {
        return imageService.getAllImages();
    }

    @GetMapping(path = "info/{filename}")
    public Image getImageInfoByFilename(@PathVariable("filename") String filename) {
        return imageService.getImageByFilename(filename);
    }

    @GetMapping("{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Get the image resource and content type from the service
            Resource resource = imageService.getImageAsResource(filename);
            String contentType = imageService.getImageContentType(filename);

            // Return the image with the correct headers
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
