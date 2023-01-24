package filemanager.service;

import filemanager.dto.TokenDto;
import filemanager.dto.UserRequestDto;
import filemanager.entity.FolderEntity;
import filemanager.entity.UserEntity;
import filemanager.repository.UserRepository;
import filemanager.security.JwtTokenService;
import filemanager.utils.exceptions.UserAlreadyExistsException;
import filemanager.utils.exceptions.UserLoginFailedException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;



    @Override
    public TokenDto checkUserCredentials(UserRequestDto userRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(userRequestDto.getUsername());
        if(userEntity!=null && passwordEncoder.matches(userRequestDto.getPassword(),userEntity.getPassword())){
            String token = jwtTokenService.createJwtToken(userEntity.getId());
            return new TokenDto(token);
        }
        else throw new UserLoginFailedException("Username does not match the password!");
    }

    @Override
    public void registerUser(UserRequestDto user) {
        FolderEntity rootFolder = new FolderEntity(user.getUsername());
        UserEntity userToAdd = new UserEntity(user.getUsername(),passwordEncoder.encode(user.getPassword()),rootFolder);
        if(userRepository.findByUsername(userToAdd.getUsername())==null)
            userRepository.save(userToAdd);
        else
            throw new UserAlreadyExistsException("User with this username already exists!");
    }
}
