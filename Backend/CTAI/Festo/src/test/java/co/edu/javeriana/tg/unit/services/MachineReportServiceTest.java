package co.edu.javeriana.tg.unit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.services.MachineReportService;

@ExtendWith(SpringExtension.class)
public class MachineReportServiceTest {

    @Mock
    private MachineReportService machineReportService;

    private final MachineReportPK testID=getWithUniqueID(1l);

    private final MachineReportPK getWithUniqueID(Long id){
        return new MachineReportPK(id, id, Date.from(Instant.ofEpochMilli(id)));
    }

    @Test
    public void testGetAll(){
        MachineReport machineReport_1 = new MachineReport(testID);
        Long resourceID = 1l;
        when(machineReportService.getAll()).thenReturn(Arrays.asList(new MachineReportDTO(machineReport_1, new ResourceDTO(new Resource(resourceID)))));
        assertEquals(testID.getTimestamp(), machineReportService.getAll().get(0).getReport().getTimestamp());
    }

    @Test
    public void testGetForMachine(){
        MachineReport machineReport_1 = new MachineReport(testID);
        Long resourceID = 1l;
        machineReport_1.setResource(resourceID);
        assertEquals(0, machineReportService.getForMachine(resourceID).size());
    }
}
