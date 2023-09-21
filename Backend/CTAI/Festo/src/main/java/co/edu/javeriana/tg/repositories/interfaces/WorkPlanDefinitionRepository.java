package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;

public interface WorkPlanDefinitionRepository extends CrudRepository<WorkPlanDefinition, Long>{
    List<WorkPlanDefinition> findAll();

    List<WorkPlanDefinition> findByWorkPlanType(Long type);
}
