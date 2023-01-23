package filemanager.repository;

import filemanager.entity.FolderEntity;
import filemanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderEntity,Long> {
}
