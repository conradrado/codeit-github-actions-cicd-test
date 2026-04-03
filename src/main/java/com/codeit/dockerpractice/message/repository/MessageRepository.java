package com.codeit.dockerpractice.message.repository;

import com.codeit.dockerpractice.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
