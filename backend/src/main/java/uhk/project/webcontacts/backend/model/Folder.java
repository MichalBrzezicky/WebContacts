package uhk.project.webcontacts.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity(name = "Folder")
@Table(name = "folders")
@Data()
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @Size(max = 100, min = 1, message = "Název musí být delší. Maximálně však 100 znaků")
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Size(max = 250, min = 1, message = "Popisek musí být delší. Maximálně však 250 znaků")
    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;
}
