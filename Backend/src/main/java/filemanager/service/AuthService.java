package filemanager.service;

import filemanager.dto.UserLoginRequestDto;
import filemanager.dto.UserLoginResponseDto;

public interface AuthService {
    UserLoginResponseDto checkUserCredentials(UserLoginRequestDto userLoginRequestDto);
}
