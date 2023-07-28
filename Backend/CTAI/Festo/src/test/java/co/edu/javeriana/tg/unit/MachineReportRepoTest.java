package co.edu.javeriana.tg.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import co.edu.javeriana.tg.entities.managed.MachineReportPK;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class MachineReportRepoTest {
    @Autowired
    MachineReportRepository machineReportRepository;

    /*@Autowired
    OperationRepository operationRepository;

    @Autowired
    OrderPositionRepository orderPositionRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PartRepository partRepository;

    @Autowired
    ResourceForOperationRepository resourceForOperationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    StepDefinitionRepository stepDefinitionRepository;

    @Autowired
    StepParameterDefinitionRepository stepParameterDefinitionRepository;

    @Autowired
    TopologyRepository topologyRepository;

    @Autowired
    WorkPlanDefinitionRepository workPlanDefinitionRepository;

    @Autowired
    WorkPlanTypeRepository workPlanTypeRepository; */

    private MachineReportPK testID = getWithUniqueID(1L);

    private List<MachineReportPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private MachineReportPK getWithUniqueID(Long id){
        return new MachineReportPK(id, id, Date.from(Instant.ofEpochMilli(id)));
    }

    @Test
    public void testEmptyCRUD() {
        MachineReport emptyOrder = new MachineReport();
        List<MachineReport> createdList = idsList.stream().map(id -> new MachineReport())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> machineReportRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> machineReportRepository.save(createdElement)));
        // Read
        assertEquals(machineReportRepository.count(), 0);
        assertEquals(Optional.empty(), machineReportRepository.findById(testID));
        machineReportRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setBusy(true);
        assertThrows(JpaSystemException.class, () -> machineReportRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setBusy(true);
            assertThrows(JpaSystemException.class, () -> machineReportRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> machineReportRepository.delete(emptyOrder));
        machineReportRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> machineReportRepository.delete(element)));
        assertEquals(machineReportRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        MachineReport order = new MachineReport(testID);
        List<MachineReport> createdList = idsList.stream().map(id -> new MachineReport(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> machineReportRepository.save(order));
        assertEquals(order, machineReportRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> machineReportRepository.save(createdElement)));
        assertEquals(order, machineReportRepository.findById(testID).get());
        // Read
        assertEquals(machineReportRepository.count(), 6);
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
        assertDoesNotThrow(() -> machineReportRepository.delete(order));
        machineReportRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> machineReportRepository.delete(element)));
        assertEquals(machineReportRepository.count(), 0);
    }
}
