package com.example.springuploadphoto.service;

import com.example.springuploadphoto.dao.entity.Image;
import com.example.springuploadphoto.dao.repo.ImageRepository;
import com.example.springuploadphoto.model.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    public Image uploadImage(MultipartFile file) throws IOException {

        Image image = repository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        return image;
    }

    public byte[] downloadImage(Long id /*String fileName*/) {
//      Optional<Image> image = repository.findByName(fileName); // Search by name
        Optional<Image> image = repository.findById(id);         // Search by id

        byte[] images = ImageUtil.decompressImage(image.get().getImageData());
        return images;
    }

    public void deleteImage(Long id /*String fileName*/) {
//      Optional<Image> image = repository.deleteByName(fileName);    // Delete by name
//      repository.deleteById(id);                                    // Delete by id

        if (repository.findById(id).isEmpty()) {
            log.error("This id not found: " + id);
        } else {
            repository.deleteById(id);
            log.info("Image deleted");
        }
    }

}