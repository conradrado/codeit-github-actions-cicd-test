package com.codeit.dockerpractice.message.service;

import com.codeit.dockerpractice.message.dto.MessageCreateRequest;
import com.codeit.dockerpractice.message.dto.MessageResponse;
import com.codeit.dockerpractice.message.entity.Message;
import com.codeit.dockerpractice.message.repository.MessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
  private final MessageRepository messageRepository;

  public MessageResponse create(MessageCreateRequest req){
    Message savedMsg = messageRepository.save(new Message(req.getContent()));
    return new MessageResponse(
        savedMsg.getId(),
        savedMsg.getContent(),
        savedMsg.getCreatedAt());
  }


  @Transactional(readOnly = true)
  public List<MessageResponse> findAll(){
    return messageRepository.findAll().stream()
        .map(message -> {
          return new MessageResponse(message.getId(), message.getContent(), message.getCreatedAt());
        }).toList();
  }

}
