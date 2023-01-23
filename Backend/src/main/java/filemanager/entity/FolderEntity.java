package filemanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "folders")
@Entity
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "parentFolder")
    private List<FileEntity> files;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "parentFolder")
    private List<FolderEntity> childFolders;

    @ManyToOne
    @JoinColumn(name="parent_folder_id")
    private FolderEntity parentFolder;
}
