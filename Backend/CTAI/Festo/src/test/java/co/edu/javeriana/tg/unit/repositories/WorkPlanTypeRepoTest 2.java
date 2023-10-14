package co.edu.javeriana.tg.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.WorkPlanType;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkPlanTypeRepoTest{
    @Autowired
    WorkPlanTypeRepository workPlanTypeRepository;

    private Long testID = getWithUniqueID(1L);

    private List<Long> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private Long getWithUniqueID(Long id){
        return id;
    }

    @Test
    public void testEmptyCRUD() {
        WorkPlanType emptyOrder = new WorkPlanType();
        List<WorkPlanType> createdList = idsList.stream().map(id -> new WorkPlanType())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> workPlanTypeRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> workPlanTypeRepository.save(createdElement)));
        // Read
        assertEquals(workPlanTypeRepository.count(), 0);
        assertEquals(Optional.empty(), workPlanTypeRepository.findById(testID));
        workPlanTypeRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setDescription("Name");
        assertThrows(JpaSystemException.class, () -> workPlanTypeRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Name");
            assertThrows(JpaSystemException.class, () -> workPlanTypeRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> workPlanTypeRepository.delete(emptyOrder));
        workPlanTypeRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> workPlanTypeRepository.delete(element)));
        assertEquals(workPlanTypeRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        WorkPlanType order = new WorkPlanType(testID);
        List<WorkPlanType> createdList = idsList.stream().map(id -> new WorkPlanType(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> workPlanTypeRepository.save(order));
        assertEquals(order, workPlanTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> workPlanTypeRepository.save(createdElement)));
        assertEquals(order, workPlanTypeRepository.findById(testID).get());
        // Read
        assertEquals(workPlanTypeRepository.count(), 6);
        assertEquals(order, workPlanTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, workPlanTypeRepository.findById(getWithUniqueID(createdElement.getTypeNumber())).get()));
        // Update
        order.setDescription("Name");
        assertDoesNotThrow(() -> workPlanTypeRepository.save(order));
        assertEquals(order, workPlanTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Name");
            assertDoesNotThrow(() -> workPlanTypeRepository.save(createdElement));
            assertEquals(createdElement, workPlanTypeRepository.findById(getWithUniqueID(createdElement.getTypeNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> workPlanTypeRepository.delete(order));
        workPlanTypeRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> workPlanTypeRepository.delete(element)));
        assertEquals(workPlanTypeRepository.count(), 0);
    }
}
