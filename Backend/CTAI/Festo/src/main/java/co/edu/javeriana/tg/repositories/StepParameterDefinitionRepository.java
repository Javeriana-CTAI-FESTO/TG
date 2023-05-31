package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.StepParameterDefinition;
import co.edu.javeriana.tg.entities.StepParameterDefinitionPK;

public interface StepParameterDefinitionRepository extends CrudRepository<StepParameterDefinition, StepParameterDefinitionPK>{
    List<StepParameterDefinition> findAll();
}
