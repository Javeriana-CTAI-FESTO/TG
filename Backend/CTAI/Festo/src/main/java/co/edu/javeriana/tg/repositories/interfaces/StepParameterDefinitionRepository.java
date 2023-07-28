package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.StepParameterDefinition;
import co.edu.javeriana.tg.entities.managed.StepParameterDefinitionPK;

public interface StepParameterDefinitionRepository extends CrudRepository<StepParameterDefinition, StepParameterDefinitionPK>{
    List<StepParameterDefinition> findAll();
}
