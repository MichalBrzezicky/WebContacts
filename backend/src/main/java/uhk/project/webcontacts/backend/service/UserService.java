package uhk.project.webcontacts.backend.service;

import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.model.User;

import java.util.List;

@Service
public interface UserService {
    public User addUser(User user);
    public User getUserById(long id);
    public List<User> getUsers();

    public void deleteUser(long id);
}
