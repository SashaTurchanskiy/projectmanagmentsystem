package com.zosh.projectmanagmentsystem.service.project;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, User user);
    List<Project> getProjectByTeam(User user, String category, String tag);
    Project getProjectById(Long projectId);
    void deleteProject(Long projectId, Long userId);
    Project updateProject(Project project, Long id);
    void addUserToProject(Long projectId, Long userId);
    void removeUserFromProject(Long projectId, Long userId);
    Chat getChatByProjectId(Long projectId);
}
