package co.edu.javeriana.tg.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;

public interface AdminInterface {
    public ResponseEntity<List<MachineReportDTO>> getAllReports();

    public ResponseEntity<List<ReportDTO>> getReportsForMachine(Long resourceId);
    
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans();

    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes();
    
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(Long typeId);

    public ResponseEntity<List<PartDTO>> getAllParts();

    public ResponseEntity<PartDTO> createPart(CreatePartAux createPart);

    public ResponseEntity<Map<Long, String>> getAllPartsType();

    public ResponseEntity<List<PartDTO>> getAllPartsByType(Long typeId);

    public ResponseEntity<List<IndicatorAux>> getAllIndicators();
}
