package com.codeit.dockerpractice.message.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {
  private Long id;
  private String content;
  private LocalDateTime createdAt;
}
