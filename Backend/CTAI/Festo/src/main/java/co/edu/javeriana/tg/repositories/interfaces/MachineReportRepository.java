package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;

public interface MachineReportRepository extends CrudRepository<MachineReport, MachineReportPK>{
    List<MachineReport> findAll();

    List<MachineReport> findByResource(Long resourceId);
}