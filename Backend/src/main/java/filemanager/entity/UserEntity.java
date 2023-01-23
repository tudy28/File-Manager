package filemanager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
 //   private FolderEntity rootFolder;
}
