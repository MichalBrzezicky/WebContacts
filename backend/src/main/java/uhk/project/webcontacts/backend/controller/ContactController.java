package uhk.project.webcontacts.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uhk.project.webcontacts.backend.model.Contact;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.PhoneNumber;
import uhk.project.webcontacts.backend.service.ContactService;
import uhk.project.webcontacts.backend.service.FolderService;
import uhk.project.webcontacts.backend.service.PhoneNumberService;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping("/add")
    public Result addContact(@Valid @RequestBody Contact contact, Long id) {
        Folder folder = folderService.getById(id);
        contact.setFolder(folder);
        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        contact.setPhoneNumbers(new ArrayList());
        Contact savedContact = contactService.add(contact);
        phoneNumberService.saveAll(phoneNumbers, savedContact);

        return new Result(true, StatusCode.SUCCESS, "Kontakt byl úspěšně vytvořen", savedContact);
    }

    @GetMapping
    public Contact getContact(long id) {
        return contactService.getById(id);
    }

    @GetMapping("/all")
    public List<Contact> getContacts(long id) {
        return contactService.getAll(id);
    }

    @PostMapping("/update")
    public Result updateContact(long id, @Valid @RequestBody Contact contact) {
        Contact updatedContact = contactService.update(id, contact);
        return new Result(true, StatusCode.SUCCESS, "Kontakt byl úspěšně aktualizován", updatedContact);
    }

    @DeleteMapping("/delete")
    public Result deleteContact(long id) {
        contactService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Kontakt byl smazán");
    }
}
