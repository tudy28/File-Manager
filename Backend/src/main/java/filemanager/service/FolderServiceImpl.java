package filemanager.service;

import filemanager.dto.FileDto;
import filemanager.dto.FolderIdNameDto;
import filemanager.dto.FolderResponseDto;
import filemanager.entity.FileEntity;
import filemanager.entity.FolderEntity;
import filemanager.entity.UserEntity;
import filemanager.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public FolderResponseDto findRootFolder(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("This user does not exist!"));

        FolderEntity folderEntity = userEntity.getRootFolder();
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
    }
}
