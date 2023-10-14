package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Topology;
import co.edu.javeriana.tg.entities.managed.TopologyPK;

public interface TopologyRepository extends CrudRepository<Topology, TopologyPK>{
    List<Topology> findAll();
}
