package filemanager.entity;

import lombok.Data;

@Data
public class FileEntity {
    private String name;
    private Byte[] content;
    private FolderEntity parentFolder;
}
