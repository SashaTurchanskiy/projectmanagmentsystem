package com.zosh.projectmanagmentsystem.service.issue;

import com.zosh.projectmanagmentsystem.modal.Issue;
import com.zosh.projectmanagmentsystem.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;
    List<Issue> getIssueByProjectId(Long projectId) throws Exception;
    Issue createIssue(IssueRequest request, Long userId) throws Exception;
    //Optional<Issue> updateIssue(Long issueId, IssueRequest request, Long userId);
    void deleteIssue(Long issueId, Long userId) throws Exception;
    Issue addUserToIssue(Long issueId, Long userId) throws Exception;
    Issue updateStatus(Long issueId, String status) throws Exception;

}
