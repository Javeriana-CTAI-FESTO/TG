package co.edu.javeriana.tg.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByClientNumber(Long clientNumber);
}
