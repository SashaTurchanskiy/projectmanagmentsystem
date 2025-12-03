package com.zosh.projectmanagmentsystem.service.message;

import com.zosh.projectmanagmentsystem.modal.Chat;
import com.zosh.projectmanagmentsystem.modal.Message;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.repository.MessageRepo;
import com.zosh.projectmanagmentsystem.repository.UserRepo;
import com.zosh.projectmanagmentsystem.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final ProjectService projectService;


    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User senderUser = userRepo.findById(senderId).orElseThrow(
                ()-> new Exception("User not found" + senderId));

        Chat chat = projectService.getChatByProjectId(projectId).getProject().getChat();
        Message message = new Message();
        message.setChat(chat);
        message.setSender(senderUser);
        message.setCreatedAt(LocalDateTime.now());
        message.setContent(content);

        Message savedMessage = messageRepo.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        return messageRepo.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }
}
