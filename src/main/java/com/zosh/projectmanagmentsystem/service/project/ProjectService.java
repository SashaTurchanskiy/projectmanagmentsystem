package com.zosh.projectmanagmentsystem.service.project;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, User user);
    List<Project> getProjectByTeam(User user, String category, String tag);
    Project getProjectById(Long projectId) throws Exception;
    void deleteProject(Long projectId, Long userId) throws Exception;
    Project updateProject(Project updatedProject, Long id) throws Exception;
    void addUserToProject(Long projectId, Long userId) throws Exception;
    void removeUserFromProject(Long projectId, Long userId) throws Exception;
    Chat getChatByProjectId(Long projectId) throws Exception;
    List<Project> searchProjects(String keyword, User user);
}
