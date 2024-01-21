package uhk.project.webcontacts.backend.service;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T> {
    T add(T entity);
    T getById(long id);
    List<T> getAll(@Nullable long superEntityId);
    void delete(long id);
    T update(long id, T entity);
}
