package com.codeit.dockerpractice.message.controller;

import com.codeit.dockerpractice.message.dto.MessageCreateRequest;
import com.codeit.dockerpractice.message.dto.MessageResponse;
import com.codeit.dockerpractice.message.service.MessageService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;

  @PostMapping
  public ResponseEntity create(
      @RequestBody @Valid MessageCreateRequest req){

    MessageResponse response = messageService.create(req);
    return ResponseEntity.created(URI.create("/messages/" + response.getId())).body(response);
  }

  @GetMapping
  public ResponseEntity findAll(){
    return ResponseEntity.ok(messageService.findAll());
  }

}
