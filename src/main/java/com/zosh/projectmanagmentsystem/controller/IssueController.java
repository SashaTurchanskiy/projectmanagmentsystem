package com.zosh.projectmanagmentsystem.controller;

import com.zosh.projectmanagmentsystem.dto.IssueDto;
import com.zosh.projectmanagmentsystem.modal.Issue;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.request.IssueRequest;
import com.zosh.projectmanagmentsystem.response.AuthResponse;
import com.zosh.projectmanagmentsystem.response.MessageResponse;
import com.zosh.projectmanagmentsystem.service.issue.IssueService;
import com.zosh.projectmanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Issue> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(projectId));
    }
    @PostMapping("/create")
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest request,
                                                @RequestHeader("Authorization")String jwt) throws Exception {

        User tokenUser = userService.findUserProfileByJwt(jwt);
        User user = userService.findUserById(tokenUser.getId());


            Issue createIssue = issueService.createIssue(request, tokenUser);
            IssueDto issueDto = new IssueDto();
            issueDto.setId(createIssue.getId());
            issueDto.setTitle(createIssue.getTitle());
            issueDto.setDescription(createIssue.getDescription());
            issueDto.setStatus(createIssue.getStatus());
            issueDto.setProjectId(createIssue.getProjectId());
            issueDto.setPriority(createIssue.getPriority());
            issueDto.setDueDate(createIssue.getDueDate().toString());
            issueDto.setTags(createIssue.getTags());
            issueDto.setProject(createIssue.getProject());
            issueDto.setAssignee(createIssue.getAssignee());

            return ResponseEntity.ok(issueDto);
    }
    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                                @RequestHeader("Authorization")String jwt) throws Exception {
        var userToken = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, userToken.getId());

        MessageResponse response = new MessageResponse();
        response.setMessage("Issue deleted successfully");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{issueId}/addUser")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @RequestParam Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status,
                                                   @PathVariable Long issueId) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}
