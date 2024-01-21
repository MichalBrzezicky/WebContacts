package uhk.project.webcontacts.backend.service;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.model.Contact;
import uhk.project.webcontacts.backend.repository.ContactRepository;
import uhk.project.webcontacts.backend.system.exception.ObjectNotFoundException;

import java.util.List;

@Service
@Transactional
public class ContactService implements BaseService<Contact> {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getById(long id) {
        return contactRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Contact", id)
        );
    }

    @Override
    public List<Contact> getAll(@Nullable long folderId) {
        return contactRepository.findAllByFolderId(folderId);
    }

    @Override
    public void delete(long id) {
        contactRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Contact", id)
        );
        contactRepository.deleteById(id);
    }

    @Override
    public Contact update(long id, Contact entity) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Contact", id)
        );
        contact.setName(entity.getName());
        return contactRepository.save(contact);
    }
}
