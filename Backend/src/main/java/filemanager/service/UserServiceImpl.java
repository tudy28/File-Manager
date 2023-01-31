package filemanager.service;

import filemanager.dto.UserResponseDto;
import filemanager.entity.UserEntity;
import filemanager.repository.UserRepository;
import filemanager.utils.exceptions.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDto findSignedInUser(Long userId) {
        return userRepository.findById(userId)
                .map(userEntity -> new UserResponseDto(userEntity.getUsername(), userEntity.getRootFolder().getId()))
                .orElseThrow(() -> new UserDoesNotExistException("This user does not exist!"));
    }
}
