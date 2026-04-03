package com.codeit.dockerpractice.message.dto;

import java.time.Instant;

public record FileResponseDto (
    String key,
    String url,
    long size,
    Instant lastModified
){

}
