package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;

public interface StepDefinitionRepository extends CrudRepository<StepDefinition, StepDefinitionPK>{
    List<StepDefinition> findAll();

    List<StepDefinition> findByWorkPlan(Long workPlanNumber);

    Long countByWorkPlan(Long workPlanNumber);
}
