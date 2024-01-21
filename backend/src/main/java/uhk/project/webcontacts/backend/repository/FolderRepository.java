package uhk.project.webcontacts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uhk.project.webcontacts.backend.model.Folder;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUserId(Long userId);
}
