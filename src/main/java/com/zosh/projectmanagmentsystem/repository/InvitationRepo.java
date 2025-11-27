package com.zosh.projectmanagmentsystem.repository;

import com.zosh.projectmanagmentsystem.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepo extends JpaRepository<Invitation, Long> {
    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
}
