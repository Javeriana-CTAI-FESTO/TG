package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.WorkPlanType;

public interface WorkPlanTypeRepository extends CrudRepository<WorkPlanType, Long>{
    List<WorkPlanType> findAll();

    String findDescriptionByTypeNumber(Long typeNumber);
}
