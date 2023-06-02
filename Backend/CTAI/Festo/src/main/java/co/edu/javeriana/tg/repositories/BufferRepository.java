package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Buffer;
import co.edu.javeriana.tg.entities.BufferPK;

public interface BufferRepository extends CrudRepository<Buffer, BufferPK> {
    List<Buffer> findAll();
}