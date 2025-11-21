package com.zosh.projectmanagmentsystem.service.user;

import com.zosh.projectmanagmentsystem.modal.User;

public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long userId) throws Exception;
    User updateUserProjectSize(User user, int number);
}
