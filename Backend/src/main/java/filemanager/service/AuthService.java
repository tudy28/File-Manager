package filemanager.service;

import filemanager.dto.TokenDto;
import filemanager.dto.UserRequestDto;

public interface AuthService {
    TokenDto checkUserCredentials(UserRequestDto userRequestDto);

    void registerUser(UserRequestDto user);

}
