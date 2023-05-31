package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.Topology;
import co.edu.javeriana.tg.entities.TopologyPK;

public interface TopologyRepository extends CrudRepository<Topology, TopologyPK>{
    List<Topology> findAll();
}
