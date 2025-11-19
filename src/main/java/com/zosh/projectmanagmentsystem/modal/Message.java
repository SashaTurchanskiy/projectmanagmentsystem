package com.zosh.projectmanagmentsystem.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;


}
