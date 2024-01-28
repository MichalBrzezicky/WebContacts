package uhk.project.webcontacts.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity(name = "PhoneNumber")
@Table(name = "phone_numbers")
@Data()
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Size(max = 6, min = 2, message = "Předčíslí musí obsahovat 2-6 čísel")
    @Column(name = "codeArea", nullable = false, columnDefinition = "TEXT")
    private String codeArea;

    @Size(max = 20, min = 5, message = "Telefonní číslo musí obsahovat 5-20 čísel")
    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    private String number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
