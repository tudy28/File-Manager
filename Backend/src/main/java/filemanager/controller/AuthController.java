package filemanager.controller;

import filemanager.dto.TokenDto;
import filemanager.dto.UserRequestDto;
import filemanager.service.AuthService;
import filemanager.utils.exceptions.UserAlreadyExistsException;
import filemanager.utils.exceptions.UserLoginFailedException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserRequestDto userRequestDto){
        try{
            TokenDto tokenDto = authService.checkUserCredentials(userRequestDto);
            return ResponseEntity.ok(tokenDto);
        }
        catch (UserLoginFailedException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userRequestDto){
        try{
            authService.registerUser(userRequestDto);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        }
        catch (UserAlreadyExistsException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
