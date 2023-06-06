package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.StepDefinition;

public interface StepDefinitionRepository extends CrudRepository<StepDefinition, Long>{
    List<StepDefinition> findAll();

    List<StepDefinition> findByWorkPlanWorkPlanNumber(Long workPlanNumber);

    Long countByWorkPlanWorkPlanNumber(Long workPlanNumber);
}
