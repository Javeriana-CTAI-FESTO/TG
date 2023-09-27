package co.edu.javeriana.tg.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;
import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MachineReportRepoTest {
    @Autowired
    MachineReportRepository machineReportRepository;

    @Autowired
    ResourceRepository resourceRepository;

    private List<Long> idsListR = List.of(1L, 2L, 3L, 4L, 5L,6L);

    private MachineReportPK testID = getWithUniqueID(1L);

    private List<MachineReportPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private MachineReportPK getWithUniqueID(Long id){
        return new MachineReportPK(id, id, Date.from(Instant.ofEpochMilli(id)));
    }
    
    @Test
    public void testNonEmptyCRUD() {
        MachineReport order = new MachineReport(testID);
        List<MachineReport> createdList = idsList.stream().map(id -> new MachineReport(id))
                .collect(Collectors.toList());
        List<Resource> createdResourcesList = idsListR.stream().map(id -> new Resource(id))
                .collect(Collectors.toList());
        // Create
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> resourceRepository.save(createdElement)));
        assertDoesNotThrow(() -> machineReportRepository.save(order));
        assertEquals(order, machineReportRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> machineReportRepository.save(createdElement)));
        assertEquals(order, machineReportRepository.findById(testID).get());
        // Read
        assertEquals(machineReportRepository.findAll().size(), 6);
        assertEquals(order, machineReportRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, machineReportRepository.findById(getWithUniqueID(createdElement.getId())).get()));
        // Update
        order.setBusy(true);
        assertDoesNotThrow(() -> machineReportRepository.save(order));
        assertEquals(order, machineReportRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setBusy(true);
            assertDoesNotThrow(() -> machineReportRepository.save(createdElement));
            assertEquals(createdElement, machineReportRepository.findById(getWithUniqueID(createdElement.getId())).get());
        });
        // Delete
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> resourceRepository.delete(createdElement)));
        assertDoesNotThrow(() -> machineReportRepository.delete(order));
        machineReportRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> machineReportRepository.delete(element)));
    }
}
