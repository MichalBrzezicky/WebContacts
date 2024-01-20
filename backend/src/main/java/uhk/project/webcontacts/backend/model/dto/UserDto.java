package uhk.project.webcontacts.backend.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import uhk.project.webcontacts.backend.model.Folder;

import java.util.List;

@Data
public class UserDto {
    private long id;
    @NotEmpty(message = "Email je povinný údaj.")
    private String email;
    @NotEmpty(message = "Role jsou povinný údaj.")
    private String roles;
    @NotEmpty(message = "Jméno je povinný údaj.")
    private String name;
    @NotEmpty(message = "Příjmení je povinný údaj.")
    private String surname;
}
