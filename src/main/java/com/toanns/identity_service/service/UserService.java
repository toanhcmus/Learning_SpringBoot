package com.toanns.identity_service.service;

import com.toanns.identity_service.dto.request.UserCreationRequest;
import com.toanns.identity_service.dto.request.UserUpdateRequest;
import com.toanns.identity_service.dto.response.UserResponse;
import com.toanns.identity_service.entity.User;
import com.toanns.identity_service.exception.AppException;
import com.toanns.identity_service.exception.ErrorCode;
import com.toanns.identity_service.mapper.UserMapper;
import com.toanns.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
   UserRepository userRepository;
   UserMapper userMapper;

    public User createUser(UserCreationRequest request) {


        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
