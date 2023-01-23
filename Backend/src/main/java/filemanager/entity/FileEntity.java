package filemanager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "files")
@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private Byte[] content;

    @ManyToOne
    @JoinColumn(name="parent_folder_id")
    private FolderEntity parentFolder;
}
