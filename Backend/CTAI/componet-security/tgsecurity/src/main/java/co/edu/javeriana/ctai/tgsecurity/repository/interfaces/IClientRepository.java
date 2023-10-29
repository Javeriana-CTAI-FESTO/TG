package co.edu.javeriana.ctai.tgsecurity.repository.interfaces;

import co.edu.javeriana.ctai.tgsecurity.entities.User;
import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByIdentificacion(Long identificacion);
    Boolean existsByCorreoElectronico(String correo);
    Boolean existsByCelular(Long celular);
    Cliente findByIdentificacion(Long identificacion);
    Cliente findByUsuario(User usuario);
}
