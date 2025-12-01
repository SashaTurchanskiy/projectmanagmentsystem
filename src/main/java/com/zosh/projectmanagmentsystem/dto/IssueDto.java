package com.zosh.projectmanagmentsystem.dto;

import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IssueDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private String dueDate;
    private List<String> tags = new ArrayList<>();
    private Project project;
    private User assignee;
}
