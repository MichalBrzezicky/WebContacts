package uhk.project.webcontacts.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity(name = "Contact")
@Table(name = "contacts")
@Data()
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> phoneNumbers;

    @Size(max = 100, min = 1, message = "Název musí být delší. Maximálně však 100 znaků")
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;
}
