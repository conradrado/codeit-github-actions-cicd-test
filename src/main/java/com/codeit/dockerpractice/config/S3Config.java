package com.codeit.dockerpractice.config;

import com.codeit.dockerpractice.AwsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {
  private final AwsProperties props;

  public S3Config(AwsProperties props) {
    this.props = props;
  }

  @Bean
  public S3Client s3Client() {
    Region region = resolveRegion();

    if (props.getCredentials().getAccessKey() != null
        && !props.getCredentials().getAccessKey().isBlank()) {
      return S3Client.builder()
          .region(region)
          .credentialsProvider(
              StaticCredentialsProvider.create(
                  AwsBasicCredentials.create(
                      props.getCredentials().getAccessKey(),
                      props.getCredentials().getSecretKey())))
          .build();
    }

    return S3Client.builder()
        .region(region)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();
  }

  @Bean
  public S3Presigner s3Presigner() {
    Region region = resolveRegion();

    if (props.getCredentials().getAccessKey() != null
        && !props.getCredentials().getAccessKey().isBlank()) {
      return S3Presigner.builder()
          .region(region)
          .credentialsProvider(
              StaticCredentialsProvider.create(
                  AwsBasicCredentials.create(
                      props.getCredentials().getAccessKey(),
                      props.getCredentials().getSecretKey())))
          .build();
    }

    return S3Presigner.builder()
        .region(region)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();
  }

  private Region resolveRegion() {
    String configuredRegion = props.getRegion();
    if (configuredRegion == null || configuredRegion.isBlank()) {
      return Region.US_EAST_1;
    }
    return Region.of(configuredRegion);
  }
}
