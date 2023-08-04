package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByClientNumber(Long clientNumber);
}
