package filemanager.service;

import filemanager.dto.FileDto;
import filemanager.dto.FolderCreateRequestDto;
import filemanager.dto.FolderIdNameDto;
import filemanager.dto.FolderResponseDto;
import filemanager.entity.FileEntity;
import filemanager.entity.FolderEntity;
import filemanager.entity.UserEntity;
import filemanager.repository.FolderRepository;
import filemanager.repository.UserRepository;
import filemanager.utils.exceptions.FolderDoesNotExistException;
import filemanager.utils.exceptions.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public FolderResponseDto findFolderById(Long folderId) {
        return folderRepository.findById(folderId)
                .map(folderEntity -> {

                    List<FolderIdNameDto> folderDtoList = folderEntity.getChildFolders()
                            .stream()
                            .map(f -> new FolderIdNameDto(f.getId(), f.getName()))
                            .collect(Collectors.toList());

                    List<FileDto> fileDtoList = folderEntity.getFiles()
                            .stream()
                            .map(f -> new FileDto(f.getId(), f.getName(), f.getContent(), new FolderIdNameDto(f.getParentFolder().getId(), f.getParentFolder().getName())))
                            .collect(Collectors.toList());

                    //check for the parent folder to not be null (this happens when the folder is root)
                    FolderIdNameDto parentFolderDto = folderEntity.getParentFolder() != null ?
                            new FolderIdNameDto(folderEntity.getParentFolder().getId(), folderEntity.getParentFolder().getName()) : null;

                    return new FolderResponseDto(folderEntity.getId(), folderEntity.getName(), fileDtoList, folderDtoList, parentFolderDto);
                })
                .orElseThrow(() -> new FolderDoesNotExistException("This folder does not exist!"));
    }

    @Override
    public FolderIdNameDto createNewFolder(FolderCreateRequestDto folderCreateRequestDto) {
        return folderRepository.findById(folderCreateRequestDto.getFolderParentId())
                .map(parentFolder -> {
                            FolderEntity newFolder = new FolderEntity(folderCreateRequestDto.getFolderName());
                            newFolder.setParentFolder(parentFolder);
                            FolderEntity createdFolder = folderRepository.save(newFolder);
                            return new FolderIdNameDto(createdFolder.getId(),createdFolder.getName());
                        })
                .orElseThrow(() -> new FolderDoesNotExistException("This folder does not exist!"));
    }

    @Override
    public FolderIdNameDto renameFolder(FolderIdNameDto folderIdNameDto) {
        FolderEntity oldFolder = folderRepository.findById(folderIdNameDto.getId()).isPresent() ? folderRepository.findById(folderIdNameDto.getId()).get() : null;
        if(oldFolder!=null) {
            oldFolder.setName(folderIdNameDto.getName());
            FolderEntity updatedFile = folderRepository.save(oldFolder);
            return new FolderIdNameDto(updatedFile.getId(), updatedFile.getName());
        }
        return null;
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }
}
