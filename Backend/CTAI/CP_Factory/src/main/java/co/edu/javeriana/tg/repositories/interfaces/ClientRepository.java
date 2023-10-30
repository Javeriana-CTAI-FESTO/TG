package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query("select client from Client client")
    List<Client> findAll();
    Client findByClientNumber(Long clientNumber);
}
