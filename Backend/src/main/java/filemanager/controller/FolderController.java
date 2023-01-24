package filemanager.controller;

import filemanager.dto.FolderResponseDto;
import filemanager.security.JwtTokenService;
import filemanager.service.FolderService;
import filemanager.utils.exceptions.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/folders")
public class FolderController {


    private final JwtTokenService jwtTokenService;
    private final FolderService folderService;

    @GetMapping("/user-root-folder")
    public ResponseEntity<FolderResponseDto> getRootFolderForSignedInUser(@RequestHeader("app-auth") String token){
        try{
            Long userId = jwtTokenService.getUserIdFromToken(token);
            return ResponseEntity.ok(folderService.findRootFolder(userId));
        }
        catch (UserDoesNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
