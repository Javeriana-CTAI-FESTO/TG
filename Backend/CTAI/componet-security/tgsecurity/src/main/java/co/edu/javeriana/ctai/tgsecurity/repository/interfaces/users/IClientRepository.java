package co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users;

import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.identificacion = :identificacion")
    Cliente findByIdentificacion(@Param("identificacion") Long identificacion);

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.identificacion = :identificacion")
    Boolean existsByIdentificacion(@Param("identificacion") Long identificacion);

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.correoElectronico = :correo")
    Boolean existsByCorreoElectronico(@Param("correo") String correo);

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.celular = :celular")
    Boolean existsByCelular(@Param("celular") Long celular);

    @Query("SELECT c FROM Cliente c WHERE c.usuario = :usuario")
    Cliente findByUsuario(@Param("usuario") User usuario);
}

