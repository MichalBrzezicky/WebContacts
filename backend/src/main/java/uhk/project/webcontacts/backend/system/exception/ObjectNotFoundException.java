package uhk.project.webcontacts.backend.system.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String entity, String id) {
        super(entity + " with id " + id + " not found!");
    }

    public ObjectNotFoundException(String entity, long id) {
        super(entity + " with id " + id + " not found!");
    }

    public ObjectNotFoundException(String entity) {
        super(entity + " not found!");
    }
}
