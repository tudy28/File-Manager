package filemanager.controller;

import filemanager.dto.FolderResponseDto;
import filemanager.dto.UserResponseDto;
import filemanager.security.JwtTokenService;
import filemanager.service.UserService;
import filemanager.utils.exceptions.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    @GetMapping("/signed-in-user")
    public ResponseEntity<UserResponseDto> getSignedInUserInfo(@RequestHeader("app-auth") String token){
        try{
            Long userId = jwtTokenService.getUserIdFromToken(token);
            return ResponseEntity.ok(userService.findSignedInUser(userId));
        }
        catch (UserDoesNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
