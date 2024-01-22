package uhk.project.webcontacts.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhk.project.webcontacts.backend.model.Folder;
import uhk.project.webcontacts.backend.model.User;
import uhk.project.webcontacts.backend.repository.FolderRepository;
import uhk.project.webcontacts.backend.system.exception.ObjectNotFoundException;

import java.util.List;

@Service
@Transactional
public class FolderService implements BaseService<Folder> {
    @Autowired
    private FolderRepository folderRepository;

    @Override
    public Folder add(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public Folder getById(long id) {
        return folderRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Folder", id)
        );
    }

    @Override
    public List<Folder> getAll(long userId) {
        return folderRepository.findByUserId(userId);
    }

    public List<Folder> getAll(User user) {
        return folderRepository.findByUser(user);
    }

    @Override
    public void delete(long id) {
        folderRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Folder", id)
        );
        folderRepository.deleteById(id);
    }

    @Override
    public Folder update(long id, Folder entity) {
        Folder folder = folderRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Folder", id)
        );
        folder.setName(entity.getName());
        folder.setTitle(entity.getTitle());
        return folderRepository.save(folder);
    }
}
