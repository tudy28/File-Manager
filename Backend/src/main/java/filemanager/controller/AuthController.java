package filemanager.controller;

import filemanager.dto.TokenDto;
import filemanager.dto.UserLoginRequestDto;
import filemanager.dto.UserLoginResponseDto;
import filemanager.security.JwtTokenService;
import filemanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
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
}
