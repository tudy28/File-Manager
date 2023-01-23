package filemanager.entity;

import lombok.Data;

import java.util.List;

@Data
public class FolderEntity {
    private String name;
    private List<FileEntity> files;
    private List<FolderEntity> folders;
    private FolderEntity parentFolder;
}
