package co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users.cp_factory;

import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.users.cp_factory.Order;
import co.edu.javeriana.ctai.tgsecurity.services.cp_facrory.impl.payloads.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT NEW co.edu.javeriana.ctai.tgsecurity.services.cp_facrory.impl.payloads.OrderResponse(o.id_part, o.id_workPlan, o.cliente.identificacion, o.title, o.orderNumber, o.image_filePath) FROM Order o JOIN o.cliente c WHERE c = :cliente AND o.orderNumber IN :mesOrders")
    List<OrderResponse> findRecentOrdersByClienteAndMESOrders(@Param("cliente") Cliente cliente, @Param("mesOrders") List<Long> mesOrders);
}

