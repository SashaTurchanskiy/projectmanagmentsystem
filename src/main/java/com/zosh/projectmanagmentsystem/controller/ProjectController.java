package com.zosh.projectmanagmentsystem.controller;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.response.MessageResponse;
import com.zosh.projectmanagmentsystem.service.project.ProjectService;
import com.zosh.projectmanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
                                                     @RequestParam(required = false) String tag,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserProfileByJwt(jwt);
        var project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);
        var createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project,
                                                 @PathVariable Long projectId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserProfileByJwt(jwt);
        var updatedProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long projectId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse response = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(@RequestParam String keyword,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(@PathVariable Long projectId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);
        var chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
