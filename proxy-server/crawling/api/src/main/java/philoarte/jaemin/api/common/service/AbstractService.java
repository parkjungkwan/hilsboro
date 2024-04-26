package philoarte.jaemin.api.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class AbstractService<T> {
    public abstract Long save(T t);

    public abstract Optional<T> findById(long id);

    public abstract Page<T> findAll(Pageable pageable);

    public abstract int count();

    public abstract Optional<T> getOne(long id); //null 있는 것들만

    public abstract Long delete(T t);

    public abstract Boolean existsById(long id);

    public abstract void deleteById(long id);


}
