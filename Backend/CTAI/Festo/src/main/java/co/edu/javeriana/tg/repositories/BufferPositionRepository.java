package co.edu.javeriana.tg.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.BufferPosition;
import co.edu.javeriana.tg.entities.BufferPositionPK;

public interface BufferPositionRepository extends CrudRepository<BufferPosition, BufferPositionPK> {
    
}
