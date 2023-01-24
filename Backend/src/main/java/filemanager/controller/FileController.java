package filemanager.controller;

import filemanager.dto.FileUploadRequestDto;
import filemanager.dto.FolderResponseDto;
import filemanager.service.FileService;
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
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestBody FileUploadRequestDto fileUploadRequestDto){
        try {
            fileService.uploadFile(fileUploadRequestDto);
            return new ResponseEntity<>("File has been uploaded successfully!", HttpStatus.OK);
        }
        catch (FolderDoesNotExistException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
