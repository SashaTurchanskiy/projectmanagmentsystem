package com.zosh.projectmanagmentsystem.service.user;

import com.zosh.projectmanagmentsystem.config.JwtProvider;
import com.zosh.projectmanagmentsystem.modal.User;
import com.zosh.projectmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFormToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user == null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()){
            throw new Exception("User not found");
        }
        return userOptional.get();
    }

    @Override
    public User updateUserProjectSize(User user, int number) {
        user.setProjectSize(user.getProjectSize()+number);
        return userRepo.save(user);
    }
}
