package co.edu.javeriana.tg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.tg.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
