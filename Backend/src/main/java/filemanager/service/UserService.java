package filemanager.service;

import filemanager.dto.UserResponseDto;

public interface UserService {
    UserResponseDto findSignedInUser(Long userId);
}
