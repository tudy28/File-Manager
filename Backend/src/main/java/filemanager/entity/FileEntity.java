package filemanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "files")
@Entity
@NoArgsConstructor
public class FileEntity {

    public FileEntity(String name, Byte[] content, FolderEntity parentFolder){
        this.name=name;
        this.content=content;
        this.parentFolder=parentFolder;
    }

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
//    @JsonIgnore
    private FolderEntity parentFolder;
}
