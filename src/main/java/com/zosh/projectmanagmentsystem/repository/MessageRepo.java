package com.zosh.projectmanagmentsystem.repository;

import com.zosh.projectmanagmentsystem.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
