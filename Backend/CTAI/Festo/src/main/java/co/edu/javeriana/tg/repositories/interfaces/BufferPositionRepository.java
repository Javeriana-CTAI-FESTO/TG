package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.BufferPosition;
import co.edu.javeriana.tg.entities.managed.BufferPositionPK;

public interface BufferPositionRepository extends CrudRepository<BufferPosition, BufferPositionPK> {
    
}
