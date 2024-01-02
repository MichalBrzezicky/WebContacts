package uhk.project.webcontacts.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.config.UserPrincipal;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.repository.UserRepository;
import uhk.project.webcontacts.backend.system.exception.ObjectNotFoundException;

import java.util.List;

@Service
@Transactional
public class UserService implements BaseService<User>, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User", id)
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User", id)
        );
        userRepository.deleteById(id);
    }

    @Override
    public User update(long id, User entity) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User", id)
        );
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setEmail(entity.getEmail());
        user.setRoles(entity.getRoles());
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel s emailem " + username + " nebyl nalezen!"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
