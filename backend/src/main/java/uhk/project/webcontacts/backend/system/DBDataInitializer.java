package uhk.project.webcontacts.backend.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.service.UserService;

@Component
public class DBDataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        /*
        User admin = new User();
        admin.setName("Admin");
        admin.setSurname("Adminní");
        admin.setEmail("admin@example.com");
        admin.setPassword("12345");
        admin.setRoles("admin user");

        User user = new User();
        user.setName("User");
        user.setSurname("Userový");
        user.setEmail("user@example.com");
        user.setPassword("11111");
        user.setRoles("user");

        userService.add(admin);
        userService.add(user);*/
    }
}
