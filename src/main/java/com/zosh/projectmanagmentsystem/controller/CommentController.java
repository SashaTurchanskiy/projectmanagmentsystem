package com.zosh.projectmanagmentsystem.controller;

import com.zosh.projectmanagmentsystem.modal.Comment;
import com.zosh.projectmanagmentsystem.request.CreateCommentRequest;
import com.zosh.projectmanagmentsystem.response.MessageResponse;
import com.zosh.projectmanagmentsystem.service.comment.CommentService;
import com.zosh.projectmanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest request,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        var userId = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.createComment(
                request.getIssueId(),
                userId.getId(),
                request.getContent()
        );
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {

        var userId = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, userId.getId());
        MessageResponse newMessage = new MessageResponse();
        newMessage.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }
    @GetMapping("/issue/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.findCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


}
