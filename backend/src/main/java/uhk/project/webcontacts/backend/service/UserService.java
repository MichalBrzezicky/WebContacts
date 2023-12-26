package uhk.project.webcontacts.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements BaseService<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }


    // TODO implementation
    @Override
    public User update(User entity) {
        return null;
    }
}
