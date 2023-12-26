package uhk.project.webcontacts.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T> {
    T add(T entity);
    T getById(long id);
    List<T> getAll();
    void delete(long id);
    T update(T entity);
}
