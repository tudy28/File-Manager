package filemanager.service;

import filemanager.dto.UserToAddDto;
import filemanager.entity.FolderEntity;
import filemanager.entity.UserEntity;
import filemanager.repository.FolderRepository;
import filemanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserToAddDto user) {
        FolderEntity rootFolder = new FolderEntity();
        rootFolder.setName("root_folder_"+user.getUsername());

        UserEntity userToAdd = new UserEntity();
        userToAdd.setUsername(user.getUsername());
        userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
        userToAdd.setRootFolder(rootFolder);
        return userRepository.save(userToAdd);
    }

    @Override
    public boolean userExists(UserToAddDto user) {
        UserEntity userFound = userRepository.findByUsername(user.getUsername());
        return userFound != null;
    }
}
