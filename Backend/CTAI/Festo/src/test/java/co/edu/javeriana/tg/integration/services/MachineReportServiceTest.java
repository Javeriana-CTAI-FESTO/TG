package co.edu.javeriana.tg.integration.services;

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
import co.edu.javeriana.tg.services.MachineReportService;
import co.edu.javeriana.tg.services.ResourceService;

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
    public void testEmptyGetAll() {
        assertEquals(0, machineReportService.getAll().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        MachineReportPK primaryKey = new MachineReportPK(1l, 1l, Date.from(Instant.now()));
        when(resourceRepository.findById(1l)).thenReturn(Optional.of(new Resource(1l)));
        when(reportRepository.findAll()).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getAll().size());
    }

    @Test
    public void testEmptyGetForMachine() {
        Long resourceID = 1l;
        assertEquals(0, machineReportService.getForMachine(resourceID).size());
    }

    @Test
    public void testNonEmptyGetForMachine() {
        Long resourceID = 1l;
        MachineReportPK primaryKey = new MachineReportPK(1l, resourceID, Date.from(Instant.now()));
        when(reportRepository.findByResource(resourceID)).thenReturn(List.of(new MachineReport(primaryKey)));
        assertEquals(1, machineReportService.getForMachine(resourceID).size());
    }
}
