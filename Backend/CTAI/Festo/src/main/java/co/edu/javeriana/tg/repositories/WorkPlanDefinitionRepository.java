package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.WorkPlanDefinition;

public interface WorkPlanDefinitionRepository extends CrudRepository<WorkPlanDefinition, Long>{
    List<WorkPlanDefinition> findAll();

    List<WorkPlanDefinition> findByWorkPlanTypeTypeNumber(Long type);
}
