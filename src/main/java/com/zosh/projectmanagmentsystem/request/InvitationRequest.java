package com.zosh.projectmanagmentsystem.request;

import lombok.Data;

@Data
public class InvitationRequest {
    private String email;
    private Long projectId;
}
