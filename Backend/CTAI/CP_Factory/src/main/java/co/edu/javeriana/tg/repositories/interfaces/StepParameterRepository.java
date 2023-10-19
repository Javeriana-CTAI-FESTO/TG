package co.edu.javeriana.tg.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.StepParameter;
import co.edu.javeriana.tg.entities.managed.StepParameterPK;

public interface StepParameterRepository extends CrudRepository<StepParameter, StepParameterPK>{  
}
