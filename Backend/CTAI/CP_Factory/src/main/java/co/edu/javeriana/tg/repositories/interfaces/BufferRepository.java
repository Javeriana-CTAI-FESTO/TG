package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Buffer;
import co.edu.javeriana.tg.entities.managed.BufferPK;

public interface BufferRepository extends CrudRepository<Buffer, BufferPK> {
    @Query("select buffer from Buffer buffer")
    List<Buffer> findAll();
}