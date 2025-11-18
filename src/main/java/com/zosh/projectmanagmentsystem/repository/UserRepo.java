package com.zosh.projectmanagmentsystem.repository;

import com.zosh.projectmanagmentsystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
