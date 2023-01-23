package filemanager.controller;

import filemanager.dto.UserToAddDto;
import filemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserToAddDto userToAddDto){
        log.info("I'm here");
        if (!userService.userExists(userToAddDto)) {
            log.info("I'm here 1");
            userService.addUser(userToAddDto);
            return new ResponseEntity<>("User created succesfully!", HttpStatus.OK);
        } else {
            log.info("I'm here 2" );
            return new ResponseEntity<>("The user already exists!", HttpStatus.OK);
        }
    }
}
