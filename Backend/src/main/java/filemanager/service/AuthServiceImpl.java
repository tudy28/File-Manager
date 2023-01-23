package filemanager.service;

import filemanager.dto.UserLoginRequestDto;
import filemanager.dto.UserLoginResponseDto;
import filemanager.entity.UserEntity;
import filemanager.mapper.UserMapper;
import filemanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserLoginResponseDto checkUserCredentials(UserLoginRequestDto userLoginRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(userLoginRequestDto.getUsername());
        if(userEntity!=null && passwordEncoder.matches(userLoginRequestDto.getUsername(),userEntity.getUsername())){
            return userMapper.entityToUserLoginResponseDto(userEntity);
        }
        return null;
    }
}
