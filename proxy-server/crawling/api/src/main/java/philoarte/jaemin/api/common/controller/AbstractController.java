package philoarte.jaemin.api.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public abstract class AbstractController<T> {
    public abstract ResponseEntity<Long> save(T t);

    public abstract ResponseEntity<Optional<T>> findById(long id);

    public abstract ResponseEntity<List<T>> findAll();

    public abstract ResponseEntity<Long> count();

    public abstract ResponseEntity<T> getOne(long id); //null 있는 것들만

    public abstract ResponseEntity<Long> delete(T t);

    public abstract ResponseEntity<Boolean> existsById(long id);
}
