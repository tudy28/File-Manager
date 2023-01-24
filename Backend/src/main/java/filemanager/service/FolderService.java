package filemanager.service;

import filemanager.dto.FolderResponseDto;

public interface FolderService {

    FolderResponseDto findRootFolder(Long userId);
}
