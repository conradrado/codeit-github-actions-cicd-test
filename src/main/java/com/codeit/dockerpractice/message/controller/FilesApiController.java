package com.codeit.dockerpractice.message.controller;

import com.codeit.dockerpractice.AwsProperties;
import com.codeit.dockerpractice.message.dto.FileResponseDto;
import com.codeit.dockerpractice.s3.service.S3UploadService;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FilesApiController {
  private final S3UploadService s3UploadService;
  private final S3Presigner presigner;
  private final AwsProperties props;

  @GetMapping("/list")
  public List<FileResponseDto> list(
      @RequestParam(value = "prefix", required=false) String prefix,
      @RequestParam(value="max", required=false, defaultValue="100") int max
  ){
    return s3UploadService.list(prefix,max);
  }

  @GetMapping("/download")
  public ResponseEntity download(
      @RequestParam("key") String key,
      @RequestParam(value="filename", required = false) String fileName
  ) {
    String bucket = props.getS3().getBucket();
    String name = (fileName != null && !fileName.isBlank())
        ? fileName
        : Paths.get(key).getFileName().toString();

    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .responseContentDisposition("attachment; filename=\"" + name + "\"")
        .build();

    GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
        .getObjectRequest(getObjectRequest)
        .signatureDuration((Duration.ofMinutes(5)))
        .build();

    String signed = presigner.presignGetObject(presignRequest).url().toString();

    return ResponseEntity.status(302).location(URI.create(signed)).build();
  }

}
