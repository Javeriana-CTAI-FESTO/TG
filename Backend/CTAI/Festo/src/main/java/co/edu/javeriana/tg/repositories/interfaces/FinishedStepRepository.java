package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.FinishedStep;
import co.edu.javeriana.tg.entities.managed.FinishedStepPK;
import co.edu.javeriana.tg.entities.managed.Step;


public interface FinishedStepRepository extends CrudRepository<FinishedStep, FinishedStepPK> {
  List<FinishedStep> findByResource(Long resource);
  List<Step> findByOrderNumber(Long orderNumber);
}
