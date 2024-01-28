package uhk.project.webcontacts.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uhk.project.webcontacts.backend.config.UserPrincipal;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.service.FolderService;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("/add")
    public Result addFolder(@Valid @RequestBody Folder folder, @AuthenticationPrincipal UserPrincipal userDetails) {
        folder.setUser(userDetails.getUser());
        Folder savedFolder = folderService.add(folder);
        return new Result(true, StatusCode.SUCCESS, "Složka byla úspěšně vytvořena", savedFolder);
    }

    @GetMapping
    public Folder getFolder(long id) {
        return folderService.getById(id);
    }

    @GetMapping("/all")
    public List<Folder> getFolders(@AuthenticationPrincipal UserPrincipal userDetails, String search) {
        User user = userDetails.getUser();
        return folderService.getAll(userDetails.getUser(), search);
    }

    @PostMapping("/update")
    public Result updateFolder(long id, @Valid @RequestBody Folder folder) {
        Folder updatedFolder = folderService.update(id, folder);
        return new Result(true, StatusCode.SUCCESS, "Složka byla aktualizovaná", updatedFolder);
    }

    @DeleteMapping("/delete")
    public Result deleteFolder(long id) {
        folderService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Složka byla smazaná");
    }
}
