package com.codeit.dockerpractice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@ConfigurationProperties(prefix="aws")
@Getter
@Setter
public class AwsProperties {
  private Credentials credentials = new Credentials();
  private String region;
  private S3 s3 = new S3();

  @Getter
  @Setter
  public static class Credentials{
    private String accessKey;
    private String secretKey;
  }

  @Getter
  @Setter
  public static class S3 {
    private String bucket;
  }
}
