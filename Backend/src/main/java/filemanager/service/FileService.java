package filemanager.service;

import filemanager.dto.FileUploadRequestDto;

public interface FileService {

    void uploadFile(FileUploadRequestDto fileUploadRequestDto);
}
