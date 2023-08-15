package co.edu.javeriana.tg.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;

public interface MachineReportRepository extends CrudRepository<MachineReport, MachineReportPK>{
    List<MachineReport> findAll();

    List<MachineReport> findByResource(Long resourceId);

    @Query("select r from MachineReport r where r.errorL0 = true or r.errorL1 = true or r.errorL2 = true")
    List<MachineReport> findFails();

    @Query("select r from MachineReport r where (r.errorL0 = true or r.errorL1 = true or r.errorL2 = true) and r.resource = ?1")
    List<MachineReport> findFailsByResource(Long resourceId);
}
