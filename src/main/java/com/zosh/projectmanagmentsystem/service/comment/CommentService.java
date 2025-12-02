package com.zosh.projectmanagmentsystem.service.comment;

import com.zosh.projectmanagmentsystem.modal.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String content) throws Exception;
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment> findCommentsByIssueId(Long issueId);
}
