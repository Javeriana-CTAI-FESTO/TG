package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.Step;
import co.edu.javeriana.tg.entities.managed.StepPK;

public interface StepRepository extends CrudRepository<Step, StepPK> {
  List<Step> findByResource(Long resource);
  List<Step> findByOrderNumber(Long orderNumber);
}
