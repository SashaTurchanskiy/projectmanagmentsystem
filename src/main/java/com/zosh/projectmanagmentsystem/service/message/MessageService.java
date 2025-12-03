package com.zosh.projectmanagmentsystem.service.message;

import com.zosh.projectmanagmentsystem.modal.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;
    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
