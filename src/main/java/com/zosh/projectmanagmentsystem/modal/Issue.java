package com.zosh.projectmanagmentsystem.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User assignee;
}
