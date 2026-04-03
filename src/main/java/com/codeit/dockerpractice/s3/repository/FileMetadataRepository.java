package com.codeit.dockerpractice.s3.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<com.codeit.dockerpractice.domain.FileMetadata, UUID> {

  Optional<com.codeit.dockerpractice.domain.FileMetadata> findByS3Key(String s3Key);

  List<com.codeit.dockerpractice.domain.FileMetadata> findTop50ByOrderByCreatedAtDesc();

  List<com.codeit.dockerpractice.domain.FileMetadata> findByS3KeyStartingWithOrderByCreatedAtDesc(String prefix);

}
