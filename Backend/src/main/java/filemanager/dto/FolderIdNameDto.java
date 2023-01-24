package filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FolderIdNameDto {
    private Long id;
    private String name;
}
