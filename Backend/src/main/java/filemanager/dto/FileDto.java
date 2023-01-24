package filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDto {
    private Long id;
    private String name;
    private Byte[] content;
    private FolderIdNameDto parentFolder;
}
