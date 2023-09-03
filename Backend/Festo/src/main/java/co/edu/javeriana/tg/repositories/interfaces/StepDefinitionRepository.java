package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;

public interface StepDefinitionRepository extends CrudRepository<StepDefinition, StepDefinitionPK>{
    List<StepDefinition> findAll();

    @Query("select step from StepDefinition step where step.workPlan = ?1 order by step.stepNumber asc")
    List<StepDefinition> findByWorkPlan(Long workPlanNumber);

    @Query("select step from StepDefinition step where step.workPlan = ?1 and step.firstStep = true")
    StepDefinition findFirstByWorkPlan(Long workPlanNumber);

    Long countByWorkPlan(Long workPlanNumber);
}
