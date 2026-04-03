package com.codeit.dockerpractice.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 500)
  private String content;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  protected Message(){}

  public Message(String content) {
    this.content = content;
    this.createdAt = LocalDateTime.now();
  }
}
