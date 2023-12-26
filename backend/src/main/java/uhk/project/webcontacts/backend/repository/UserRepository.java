package uhk.project.webcontacts.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uhk.project.webcontacts.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
