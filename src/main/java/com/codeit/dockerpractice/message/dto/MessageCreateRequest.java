package com.codeit.dockerpractice.message.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageCreateRequest {
  @NotBlank(message = "content는 비어 있을 수 없어요")
  private String content;
}
