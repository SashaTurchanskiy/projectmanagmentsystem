package com.zosh.projectmanagmentsystem.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private String content;
    private Long senderId;
    private Long projectId;
}
