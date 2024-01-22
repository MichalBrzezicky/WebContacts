package uhk.project.webcontacts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.User;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query(value = "select * from folders where user_id = ?1", nativeQuery = true)
    List<Folder> findByUserId(long userId);

    List<Folder> findByUser(User user);
}
