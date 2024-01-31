package uhk.project.webcontacts.backend.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uhk.project.webcontacts.backend.model.Contact;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.PhoneNumber;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.service.ContactService;
import uhk.project.webcontacts.backend.service.FolderService;
import uhk.project.webcontacts.backend.service.PhoneNumberService;
import uhk.project.webcontacts.backend.service.UserService;

import java.lang.reflect.Array;

@Component
public class DBDataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Uživatel");
        user.setSurname("Uživatelský");
        user.setEmail("user@user.com");
        user.setPassword("12345");
        user.setRoles("user");

        User user1 = new User();
        user1.setName("User");
        user1.setSurname("Testový");
        user1.setEmail("test@test.com");
        user1.setPassword("12345");
        user1.setRoles("user");

        User savedUser = userService.add(user);
        User savedTester = userService.add(user1);

        String[] contactsNames = {"Alča Pracovní", "Dominik Nový", "Patrik Borovička", "Petr Pán", "Bar 21 u Staré kotvy" };

        for (int i = 0; i < 15; i++) {
            Folder folder = new Folder();
            folder.setName("Složka č. " + i);
            folder.setTitle("Tato složka slouží k testování...");
            folder.setUser(savedTester);
            Folder savedFolder = folderService.add(folder);

            for (int j = 0; j < 5; j++) {
                Contact contact = new Contact();
                contact.setName(contactsNames[j] + ' ' + j);
                contact.setFolder(savedFolder);
                Contact savedContact = contactService.add(contact);

                for (int k = 0; k < 3; k++) {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setCodeArea("420");
                    phoneNumber.setNumber(String.format("%d%d%d %d%d%d %d%d%d", i + 4, i + 4, i + 4, j + 4, j + 4, j + 4, k + 4, k + 4, k + 4));
                    phoneNumber.setContact(contact);
                    phoneNumberService.add(phoneNumber);
                }
            }
        }

        String[] folderNames = {"Pracovní složka", "Obchodní záležitosti", "Zábava a taxi", "Oblíbené"};
        String[] folderTitles = {"Pouze a jen práce. Nic jiného tu není", "Investice, bankovní sektor, sázky... Prostě vše, co se týče peněz.", "Rezervace, taxikáři", "Rodina a přátele" };

        for (int i = 0; i < folderNames.length; i++) {
            Folder folder = new Folder();
            folder.setUser(savedUser);
            folder.setName(folderNames[i]);
            folder.setTitle(folderTitles[i]);
            Folder savedFolder = folderService.add(folder);

            for (int j = 0; j < 5; j++) {
                Contact contact = new Contact();
                contact.setName(contactsNames[j] + ' ' + j);
                contact.setFolder(savedFolder);
                Contact savedContact = contactService.add(contact);

                for (int k = 0; k < 3; k++) {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setCodeArea("420");
                    phoneNumber.setNumber(String.format("%d%d%d %d%d%d %d%d%d", i + 5, i + 5, i + 5, j + 5, j + 5, j + 5, k + 5, k + 5, k + 5));
                    phoneNumber.setContact(contact);
                    phoneNumberService.add(phoneNumber);
                }
            }
        }

    }
}
