package co.edu.javeriana.tg.integration.services.components;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;
import co.edu.javeriana.tg.services.components.MachineReportService;
import co.edu.javeriana.tg.services.components.ResourceService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class MachineReportServiceTest {
    @Autowired
    private MachineReportService machineReportService;

    @MockBean
    private MachineReportRepository reportRepository;

    @MockBean
    private ResourceService resourceService;

    @MockBean
    private ResourceRepository resourceRepository;

    @Test
    public void testEmptyGetAllFails() {
        assertEquals(0, machineReportService.getAllMachineFails().size());
    }

    @Test
    public void testNonEmptyGetAllFails() {
        MachineReportPK primaryKey = new MachineReportPK(1l, 1l, Date.from(Instant.now()));
        when(resourceRepository.findById(1l)).thenReturn(Optional.of(new Resource(1l)));
        when(reportRepository.findFails()).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getAllMachineFails().size());
    }

    @Test
    public void testEmptyGetAllFailsForMachine() {
        Long resourceID = 1l;
        assertEquals(0, machineReportService.getAllMachineFailsForMachine(resourceID).size());
    }

    @Test
    public void testNonEmptyGetAllFailsForMachine() {
        Long resourceID = 1l;
        MachineReportPK primaryKey = new MachineReportPK(1l, resourceID, Date.from(Instant.now()));
        when(reportRepository.findFailsByResource(resourceID)).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getAllMachineFailsForMachine(resourceID).size());
    }

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, machineReportService.getAllMachineReports().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        MachineReportPK primaryKey = new MachineReportPK(1l, 1l, Date.from(Instant.now()));
        when(resourceRepository.findById(1l)).thenReturn(Optional.of(new Resource(1l)));
        when(reportRepository.findAll()).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getAllMachineReports().size());
    }

    @Test
    public void testEmptyGetForMachine() {
        Long resourceID = 1l;
        assertEquals(0, machineReportService.getMachineReportsForMachine(resourceID).size());
    }

    @Test
    public void testNonEmptyGetForMachine() {
        Long resourceID = 1l;
        MachineReportPK primaryKey = new MachineReportPK(1l, resourceID, Date.from(Instant.now()));
        when(reportRepository.findByResource(resourceID)).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getMachineReportsForMachine(resourceID).size());
    }
}
