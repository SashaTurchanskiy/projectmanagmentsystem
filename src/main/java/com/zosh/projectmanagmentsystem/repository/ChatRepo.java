package com.zosh.projectmanagmentsystem.repository;

import com.zosh.projectmanagmentsystem.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Long> {
}
