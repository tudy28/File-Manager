package filemanager.controller;

import filemanager.dto.FileDto;
import filemanager.dto.FileRenameDto;
import filemanager.dto.FileUploadRequestDto;
import filemanager.dto.FolderResponseDto;
import filemanager.service.FileService;
import filemanager.utils.exceptions.FileDoesNotExistException;
import filemanager.utils.exceptions.FolderDoesNotExistException;
import filemanager.utils.exceptions.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<FileDto> uploadFile(@RequestBody FileUploadRequestDto fileUploadRequestDto){
        try {
            return new ResponseEntity<>(fileService.uploadFile(fileUploadRequestDto), HttpStatus.OK);
        }
        catch (FolderDoesNotExistException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(fileService.getFileById(id), HttpStatus.OK);
        }
        catch (FolderDoesNotExistException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/rename")
    public ResponseEntity<FileDto> renameFile(@RequestBody FileRenameDto fileRenameDto){
        try {
            return new ResponseEntity<>(fileService.renameFile(fileRenameDto), HttpStatus.OK);
        }
        catch (FileDoesNotExistException  ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
