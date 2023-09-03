package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Step;
import co.edu.javeriana.tg.entities.managed.StepPK;

public interface StepRepository extends CrudRepository<Step, StepPK> {
  
}
