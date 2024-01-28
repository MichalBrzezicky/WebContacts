package uhk.project.webcontacts.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uhk.project.webcontacts.backend.model.Contact;
import uhk.project.webcontacts.backend.model.PhoneNumber;
import uhk.project.webcontacts.backend.service.ContactService;
import uhk.project.webcontacts.backend.service.PhoneNumberService;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/phone_number")
public class PhoneNumberController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping("/add")
    public Result addPhoneNumber(@Valid @RequestBody PhoneNumber phoneNumber, Long id) {
        Contact contact = contactService.getById(id);
        phoneNumber.setContact(contact);
        PhoneNumber savedPhoneNumber = phoneNumberService.add(phoneNumber);

        return new Result(true, StatusCode.SUCCESS, "Telefonní číslo bylo úspěšně uložené", savedPhoneNumber);
    }

    @PostMapping("add_all")
    public Result addPhoneNumbers(@Valid @RequestBody List<PhoneNumber> phoneNumbers, Long id) {
        Contact contact = contactService.getById(id);
        List<PhoneNumber> savedPhoneNumbers = phoneNumberService.saveAll(phoneNumbers, contact);

        return new Result(true, StatusCode.SUCCESS, "Telefonní čísla byla úspěšně uložena", savedPhoneNumbers);
    }

    @GetMapping
    public PhoneNumber getPhoneNumber(long id) {
        return phoneNumberService.getById(id);
    }

    @GetMapping("/all")
    public List<PhoneNumber> getPhoneNumbers(long id) {
        return phoneNumberService.getAll(id);
    }

    @PostMapping("/update")
    public Result updatePhoneNumber(long id, @Valid @RequestBody PhoneNumber phoneNumber) {
        PhoneNumber updatedPhoneNumber = phoneNumberService.update(id, phoneNumber);
        return new Result(true, StatusCode.SUCCESS, "Telefonní číslo bylo úspěšně aktualizováno", updatedPhoneNumber);
    }

    @DeleteMapping("/delete")
    public Result deletePhoneNumber(long id) {
        phoneNumberService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Telefonní číslo bylo smazáno");
    }
}
