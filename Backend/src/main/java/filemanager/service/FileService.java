package filemanager.service;

import filemanager.dto.FileContentDto;
import filemanager.dto.FileDto;
import filemanager.dto.FileRenameDto;
import filemanager.dto.FileUploadRequestDto;

public interface FileService {

    FileDto uploadFile(FileUploadRequestDto fileUploadRequestDto);
    FileContentDto getFileContentById(Long id);
    FileDto renameFile(FileRenameDto fileRenameDto);
    void deleteFile(Long id);
}
