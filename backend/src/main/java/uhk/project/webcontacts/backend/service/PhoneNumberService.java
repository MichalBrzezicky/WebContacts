package uhk.project.webcontacts.backend.service;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.model.Contact;
import uhk.project.webcontacts.backend.model.PhoneNumber;
import uhk.project.webcontacts.backend.repository.PhoneNumberRepository;
import uhk.project.webcontacts.backend.system.exception.ObjectNotFoundException;

import java.util.List;

@Service
@Transactional
public class PhoneNumberService implements BaseService<PhoneNumber> {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    public PhoneNumber add(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    @Override
    public PhoneNumber getById(long id) {
        return phoneNumberRepository.findById(id).orElseThrow(
                () ->  new ObjectNotFoundException("Phone number", id)
        );
    }

    @Override
    public List<PhoneNumber> getAll(@Nullable long phoneNumberId) {
        return phoneNumberRepository.findAllByContactId(phoneNumberId);
    }

    @Override
    public void delete(long id) {
        phoneNumberRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Phone number", id)
        );
        phoneNumberRepository.deleteById(id);
    }

    @Override
    public PhoneNumber update(long id, PhoneNumber entity) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Phone number", id)
        );

        phoneNumber.setNumber(entity.getNumber());
        phoneNumber.setCodeArea(entity.getCodeArea());
        return phoneNumberRepository.save(phoneNumber);
    }

    public List<PhoneNumber> saveAll(List<PhoneNumber> phoneNumbers, Contact contact) {
        for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumber.setContact(contact);
        }
        return phoneNumberRepository.saveAll(phoneNumbers);
    }
}
