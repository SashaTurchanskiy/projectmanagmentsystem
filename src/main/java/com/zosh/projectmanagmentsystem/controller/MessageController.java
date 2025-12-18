package com.zosh.projectmanagmentsystem.controller;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Message;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.request.CreateMessageRequest;
import com.zosh.projectmanagmentsystem.service.message.MessageService;
import com.zosh.projectmanagmentsystem.service.project.ProjectService;
import com.zosh.projectmanagmentsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {

        User user = userService.findUserById(request.getSenderId());
        Chat chat = projectService.getChatByProjectId(request.getProjectId()).getProject().getChat();
        if (chat == null) throw new Exception("Chat not found for project id: " + request.getProjectId());
        Message message = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Message> getMessageByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok().body((Message) messages);
    }

}
