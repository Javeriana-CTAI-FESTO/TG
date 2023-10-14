package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.FinishedStep;
import co.edu.javeriana.tg.entities.managed.FinishedStepPK;

public interface FinishedStepRepository extends CrudRepository<FinishedStep, FinishedStepPK> {
  List<FinishedStep> findByResource(Long resource);
  List<FinishedStep> findByOrderNumber(Long orderNumber);
}
