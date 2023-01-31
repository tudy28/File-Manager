package filemanager.controller;

import filemanager.dto.*;
import filemanager.security.JwtTokenService;
import filemanager.service.FolderService;
import filemanager.utils.exceptions.FolderDoesNotExistException;
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

    @GetMapping("/{folderId}")
    public ResponseEntity<FolderResponseDto> getFolderById(@PathVariable Long folderId){
        try{
            return ResponseEntity.ok(folderService.findFolderById(folderId));
        }
        catch (FolderDoesNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new-folder")
    public ResponseEntity<FolderIdNameDto> createNewFolder(@RequestBody FolderCreateRequestDto folderCreateRequestDto){
        try{
            return new ResponseEntity<>(folderService.createNewFolder(folderCreateRequestDto),HttpStatus.OK);
        }
        catch (FolderDoesNotExistException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/rename")
    public ResponseEntity<FolderIdNameDto> renameFolder(@RequestBody FolderIdNameDto folderIdNameDto){
        try {
            return new ResponseEntity<>(folderService.renameFolder(folderIdNameDto), HttpStatus.OK);
        }
        catch (FolderDoesNotExistException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFolder(@PathVariable Long id){
        folderService.deleteFolder(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
