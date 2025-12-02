package com.zosh.projectmanagmentsystem.service.comment;

import com.zosh.projectmanagmentsystem.modal.Comment;
import com.zosh.projectmanagmentsystem.modal.Issue;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.repository.CommentRepo;
import com.zosh.projectmanagmentsystem.repository.IssueRepo;
import com.zosh.projectmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final IssueRepo issueRepo;
    private final UserRepo userRepo;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepo.findById(issueId);
        Optional<User> userOptional = userRepo.findById(userId);
        if (issueOptional.isEmpty()){
            throw new Exception("Issue not found" + issueId);
        }
        if (userOptional.isEmpty()){
            throw new Exception("User not found" + userId);
        }
        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepo.save(comment);
        issue.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepo.findById(commentId);
        Optional<User> userOptional = userRepo.findById(userId);

        if (commentOptional.isEmpty()){
            throw new Exception("Comment not found" + commentId);
        }
        if (userOptional.isEmpty()){
            throw new Exception("User not found" + userId);
        }
        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user)){
            commentRepo.delete(comment);
        }else {
            throw new Exception("User is not authorized to delete this comment");
        }

    }

    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) {
        return commentRepo.findByIssueId(issueId);
    }
}
