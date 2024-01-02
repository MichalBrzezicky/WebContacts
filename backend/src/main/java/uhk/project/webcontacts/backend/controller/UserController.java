package uhk.project.webcontacts.backend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.model.dto.UserDto;
import uhk.project.webcontacts.backend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/all")
    public List<UserDto> getAllUsers(Authentication authentication) {

        return userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @RequestMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        }

        throw new BadCredentialsException("Invalid credentials");
    }
}
