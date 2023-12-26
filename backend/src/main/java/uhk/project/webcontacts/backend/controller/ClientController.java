package uhk.project.webcontacts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhk.project.webcontacts.backend.service.UserService;

@RestController
public class ClientController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String hello_world() {
        return "Hello World";
    }
    /*

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping("/user")
    public User getUserById(long id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/addUser")
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(long id) {
        userService.deleteUser(id);
    }*/
}
