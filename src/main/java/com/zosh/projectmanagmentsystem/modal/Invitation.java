package com.zosh.projectmanagmentsystem.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String email;
    private Long projectId;
}
