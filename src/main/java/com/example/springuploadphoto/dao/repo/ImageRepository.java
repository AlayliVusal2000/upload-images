package com.example.springuploadphoto.dao.repo;

import com.example.springuploadphoto.dao.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String fileName);
    Optional<Image> deleteByName(String fileName);

}