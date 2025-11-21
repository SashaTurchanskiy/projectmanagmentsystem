package com.zosh.projectmanagmentsystem.service.project;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.repository.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepo projectRepo;


    @Override
    public Project createProject(Project project, User user) {
        return null;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) {
        return List.of();
    }

    @Override
    public Project getProjectById(Long projectId) {
        return null;
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {

    }

    @Override
    public Project updateProject(Project project, Long id) {
        return null;
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) {

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) {

    }

    @Override
    public Chat getChatByProjectId(Long projectId) {
        return null;
    }
}
