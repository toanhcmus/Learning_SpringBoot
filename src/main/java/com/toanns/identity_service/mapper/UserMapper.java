package com.toanns.identity_service.mapper;

import com.toanns.identity_service.dto.request.UserCreationRequest;
import com.toanns.identity_service.dto.request.UserUpdateRequest;
import com.toanns.identity_service.dto.response.UserResponse;
import com.toanns.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
