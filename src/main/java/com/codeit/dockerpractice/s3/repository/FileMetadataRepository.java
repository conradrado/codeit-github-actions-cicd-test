package com.codeit.dockerpractice.s3.repository;

import com.codeit.dockerpractice.s3.entity.FileMetadata;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, UUID> {

  Optional<FileMetadata> findByS3Key(String s3Key);

  List<FileMetadata> findTop50ByOrderByCreatedAtDesc();

  List<FileMetadata> findByS3KeyStartingWithOrderByCreatedAtDesc(String prefix);

}
