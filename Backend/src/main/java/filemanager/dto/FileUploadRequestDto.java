package filemanager.dto;

import filemanager.entity.FolderEntity;
import lombok.Data;

@Data
public class FileUploadRequestDto {
    private String name;
    private Byte[] content;
    private Long parentFolderId;
}
