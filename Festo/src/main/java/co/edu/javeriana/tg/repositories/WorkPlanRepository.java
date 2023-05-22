package co.edu.javeriana.tg.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.WorkPlan;

public interface WorkPlanRepository extends CrudRepository<WorkPlan, Long>{
    List<WorkPlan> findAll();
}
