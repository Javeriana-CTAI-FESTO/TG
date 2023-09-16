package co.edu.javeriana.ctai.tgsecurity.repository;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Boolean existsByOrderNumber(Long orderNumber);
    Order findByOrderNumber(Long orderNumber);
    Order findById(long id);
    List<Order> findByCliente(Cliente cliente);
}
