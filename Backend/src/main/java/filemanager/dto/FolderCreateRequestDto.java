package filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FolderCreateRequestDto {
    private String folderName;
    private Long folderParentId;
}
