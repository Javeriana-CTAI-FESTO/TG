package co.edu.javeriana.tg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.MachineReport;
import co.edu.javeriana.tg.entities.MachineReportPK;

public interface MachineReportRepository extends CrudRepository<MachineReport, MachineReportPK>{
    List<MachineReport> findAll();
}
