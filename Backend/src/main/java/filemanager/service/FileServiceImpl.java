package filemanager.service;

import filemanager.dto.*;
import filemanager.entity.FileEntity;
import filemanager.entity.FolderEntity;
import filemanager.repository.FileRepository;
import filemanager.repository.FolderRepository;
import filemanager.utils.exceptions.FileDoesNotExistException;
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
    public FileDto uploadFile(FileUploadRequestDto fileUploadRequestDto) {
        return folderRepository.findById(fileUploadRequestDto.getParentFolderId())
                .map(existingFolder -> {
                    FileEntity fileEntity = new FileEntity(fileUploadRequestDto.getName(), fileUploadRequestDto.getContent(), existingFolder);
                    FileEntity createdFile = fileRepository.save(fileEntity);
                    FolderIdNameDto folderIdNameDto = new FolderIdNameDto(createdFile.getParentFolder().getId(), createdFile.getParentFolder().getName());
                    return new FileDto(createdFile.getId(), createdFile.getName(), createdFile.getContent(), folderIdNameDto);
                })
                .orElseThrow(() -> new FolderDoesNotExistException("This folder does not exist!"));
    }

    @Override
    public FileContentDto getFileContentById(Long id) {
        Optional<FileEntity> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            FileEntity file = fileOptional.get();
            return new FileContentDto(file.getContent());
        }
        return null;
    }

    @Override
    public FileDto renameFile(FileRenameDto fileRenameDto) {
        return fileRepository.findById(fileRenameDto.getId())
                .map(foundFile -> {
                            foundFile.setName(fileRenameDto.getName());
                            FileEntity updatedFile = fileRepository.save(foundFile);
                            FolderIdNameDto folderIdNameDto = new FolderIdNameDto(updatedFile.getParentFolder().getId(), updatedFile.getParentFolder().getName());
                            return new FileDto(updatedFile.getId(), updatedFile.getName(), updatedFile.getContent(), folderIdNameDto);
                        })
                .orElseThrow(() -> new FileDoesNotExistException("This file does not exist!"));
    }

    @Override
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
