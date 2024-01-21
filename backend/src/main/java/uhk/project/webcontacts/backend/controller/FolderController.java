package uhk.project.webcontacts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhk.project.webcontacts.backend.config.UserPrincipal;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.service.FolderService;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

@RestController
@RequestMapping("${api.endpoint.base-url}/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;
    /* @AuthenticationPrincipal UserDetails userDetails*/

    @PostMapping("/add")
    public Result addFolder(Folder folder, @AuthenticationPrincipal UserPrincipal userDetails) {
        folder.setUser(userDetails.getUser());
        Folder savedFolder = folderService.add(folder);
        return new Result(true, StatusCode.SUCCESS, "Folder added");
    }
}
