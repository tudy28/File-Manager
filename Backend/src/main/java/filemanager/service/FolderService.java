package filemanager.service;

import filemanager.dto.*;

public interface FolderService {

    FolderResponseDto findFolderById(Long userId);

    FolderIdNameDto createNewFolder(FolderCreateRequestDto folderCreateRequestDto);
    FolderIdNameDto renameFolder(FolderIdNameDto folderIdNameDto);
    void deleteFolder(Long id);
}
