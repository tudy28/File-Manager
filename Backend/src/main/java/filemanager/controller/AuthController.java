package filemanager.controller;

import filemanager.dto.TokenDto;
import filemanager.dto.UserLoginRequestDto;
import filemanager.dto.UserLoginResponseDto;
import filemanager.dto.UserToAddDto;
import filemanager.security.JwtTokenService;
import filemanager.service.AuthService;
import filemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        UserLoginResponseDto userLoginResponseDto = authService.checkUserCredentials(userLoginRequestDto);
        if(userLoginResponseDto!=null){
            String token = jwtTokenService.createJwtToken(userLoginResponseDto.getId());
            TokenDto tokenDto = new TokenDto(token);
            return ResponseEntity.ok(tokenDto);
        }
        return null;
    }

    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserToAddDto userToAddDto){
        if (!userService.userExists(userToAddDto)) {
            userService.addUser(userToAddDto);
            return new ResponseEntity<>("User created succesfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The user already exists!", HttpStatus.OK);
        }
    }
}
