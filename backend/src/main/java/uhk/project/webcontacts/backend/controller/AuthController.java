package uhk.project.webcontacts.backend.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.model.dto.UserDto;
import uhk.project.webcontacts.backend.service.UserService;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

@RestController
@RequestMapping("${api.endpoint.base-url}/auth")
public class AuthController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @RequestMapping("/all")
    public List<UserDto> getAllUsers(Authentication authentication) {

        return userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }*/

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        }

        throw new BadCredentialsException("Invalid credentials");
    }

    @PostMapping("/signup")
    public Result signup(@Valid @RequestBody User user) {
        User savedUser = userService.add(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return new Result(true, StatusCode.SUCCESS, "Registrace proběhla úspěšně", savedUserDto);
    }
}
