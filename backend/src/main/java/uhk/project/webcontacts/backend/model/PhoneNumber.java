package uhk.project.webcontacts.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "PhoneNumber")
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Size(max = 3, min = 3, message = "Předčíslí musí obsahovat přesně 3 čísla")
    @Column(name = "codeArea", nullable = false, columnDefinition = "TEXT")
    private String codeArea;
    @Size(max = 9, min = 9, message = "Telefonní číslo musí obsahovat přesně 9 čísel")
    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    private String number;

    @ManyToMany(mappedBy = "phoneNumbers", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    public String getCodeArea() {
        return codeArea;
    }

    public void setCodeArea(String codeArea) {
        this.codeArea = codeArea;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
