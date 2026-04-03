package com.codeit.dockerpractice.s3.controller;

import com.codeit.dockerpractice.s3.service.S3UploadService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class S3UploadController {
  private final S3UploadService s3UploadService;

  @PostMapping("/upload")
  public ResponseEntity upload(@RequestParam ("file") MultipartFile file){
    com.codeit.dockerpractice.domain.FileMetadata saved = fileMetadataService.uploadAndSave(file);
    FileMetadata dto = com.codeit.dockerpractice.domain.FileMetadata

    String url = s3UploadService.store(file);
    return ResponseEntity.created(URI.create(url))
        .build();

  }



}
