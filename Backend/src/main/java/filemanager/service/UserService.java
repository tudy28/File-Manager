package filemanager.service;

import filemanager.dto.UserToAddDto;
import filemanager.entity.UserEntity;

public interface UserService {
    UserEntity addUser(UserToAddDto user);
    boolean userExists(UserToAddDto user);
}
