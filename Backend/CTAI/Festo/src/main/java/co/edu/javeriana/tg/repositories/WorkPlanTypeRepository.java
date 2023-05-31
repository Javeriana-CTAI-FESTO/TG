package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.WorkPlanType;

public interface WorkPlanTypeRepository extends CrudRepository<WorkPlanType, Long>{
    List<WorkPlanType> findAll();
}
