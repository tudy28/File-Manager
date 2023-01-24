package filemanager.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "users")
@NoArgsConstructor
@Entity
public class UserEntity {

    public UserEntity(String username,String password,FolderEntity rootFolder){
        this.username=username;
        this.password=password;
        this.rootFolder=rootFolder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="root_folder_id")
    private FolderEntity rootFolder;
}
