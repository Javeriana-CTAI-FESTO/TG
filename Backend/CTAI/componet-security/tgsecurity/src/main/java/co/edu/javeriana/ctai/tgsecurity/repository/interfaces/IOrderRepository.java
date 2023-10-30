package co.edu.javeriana.ctai.tgsecurity.repository.interfaces;

import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Boolean existsByOrderNumber(Long orderNumber);
    Order findByOrderNumber(Long orderNumber);
    Order findById(long id);
    List<Order> findByCliente(Cliente cliente);
    @Query("SELECT o FROM Order o JOIN FETCH o.cliente c WHERE c = :cliente")
    List<Order> findByClienteFetchOrders(@Param("cliente") Cliente cliente);
}
