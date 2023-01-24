package filemanager.service;

import filemanager.dto.FileUploadRequestDto;
import filemanager.entity.FileEntity;
import filemanager.entity.FolderEntity;
import filemanager.repository.FileRepository;
import filemanager.repository.FolderRepository;
import filemanager.utils.exceptions.FolderDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    @Override
    public void uploadFile(FileUploadRequestDto fileUploadRequestDto) {
        folderRepository.findById(fileUploadRequestDto.getParentFolderId())
                .map(existingFolder -> {
                    FileEntity fileEntity = new FileEntity(fileUploadRequestDto.getName(), fileUploadRequestDto.getContent(), existingFolder);
                    fileRepository.save(fileEntity);
                    return fileEntity;
                })
                .orElseThrow(() -> new FolderDoesNotExistException("This folder does not exist!"));
    }
}
