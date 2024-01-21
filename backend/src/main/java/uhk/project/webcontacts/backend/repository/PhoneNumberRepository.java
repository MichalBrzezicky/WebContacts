package uhk.project.webcontacts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uhk.project.webcontacts.backend.model.PhoneNumber;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findAllByContactId(Long contactId);
}
