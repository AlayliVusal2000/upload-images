package com.example.springuploadphoto.controller;

import com.example.springuploadphoto.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(file);
        return ResponseEntity.ok("Image successfully upload.");

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        byte[] imageData = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);

    }


}
