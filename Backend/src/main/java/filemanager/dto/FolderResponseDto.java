package filemanager.dto;

import filemanager.entity.FileEntity;
import filemanager.entity.FolderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FolderResponseDto {
    private Long id;
    private String name;
    private List<FileDto> files;
    private List<FolderIdNameDto> childFolders;
    private FolderIdNameDto parentFolder;
}
