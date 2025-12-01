package com.zosh.projectmanagmentsystem.service.issue;

import com.zosh.projectmanagmentsystem.modal.Issue;
import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.repository.IssueRepo;
import com.zosh.projectmanagmentsystem.request.IssueRequest;
import com.zosh.projectmanagmentsystem.service.project.ProjectService;
import com.zosh.projectmanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService{

    private final IssueRepo issueRepo;
    private final ProjectService projectService;
    private final UserService userService;


    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepo.findById(issueId);
        if (issue.isPresent()){
            return issue.get();
        }
        throw new Exception("Issue not found");
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        List<Issue> issues = issueRepo.findByProjectId(projectId);
        if (issues == null){
            throw new Exception("No issues found for the project");
        }
        return issues;
    }

    @Override
    public Issue createIssue(IssueRequest request, User user) throws Exception {
        Project project = projectService.getProjectById(request.getProjectId());

        Issue issue = new Issue();
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setStatus(request.getStatus());
        issue.setPriority(request.getPriority());
        issue.setDueDate(request.getDueDate());
        issue.setProjectId(request.getProjectId());
        issue.setProject(project);

        return issueRepo.save(issue);
    }

  /*  @Override
    public Optional<Issue> updateIssue(Long issueId, IssueRequest request, Long userId) {
        return Optional.empty();
    }*/

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        if (issueId == null){
            throw new Exception("Issue has been deleted already");
        }
        issueRepo.deleteById(issueId);

    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issueRepo.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);
        issue.setStatus(status);
        return issueRepo.save(issue);
    }
}
