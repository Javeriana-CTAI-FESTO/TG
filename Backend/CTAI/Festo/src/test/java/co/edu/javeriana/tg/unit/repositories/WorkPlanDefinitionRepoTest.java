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

import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import co.edu.javeriana.tg.repositories.interfaces.WorkPlanDefinitionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkPlanDefinitionRepoTest{
    @Autowired
    WorkPlanDefinitionRepository workPlanDefinitionRepository;

    private Long testID = getWithUniqueID(1L);

    private List<Long> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private Long getWithUniqueID(Long id){
        return id;
    }

    @Test
    public void testEmptyCRUD() {
        WorkPlanDefinition emptyOrder = new WorkPlanDefinition();
        List<WorkPlanDefinition> createdList = idsList.stream().map(id -> new WorkPlanDefinition())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> workPlanDefinitionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> workPlanDefinitionRepository.save(createdElement)));
        // Read
        assertEquals(workPlanDefinitionRepository.count(), 0);
        assertEquals(Optional.empty(), workPlanDefinitionRepository.findById(testID));
        workPlanDefinitionRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setPictureNumber(1L);
        assertThrows(JpaSystemException.class, () -> workPlanDefinitionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setPictureNumber(1L);
            assertThrows(JpaSystemException.class, () -> workPlanDefinitionRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> workPlanDefinitionRepository.delete(emptyOrder));
        workPlanDefinitionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> workPlanDefinitionRepository.delete(element)));
        assertEquals(workPlanDefinitionRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        WorkPlanDefinition order = new WorkPlanDefinition(testID);
        List<WorkPlanDefinition> createdList = idsList.stream().map(id -> new WorkPlanDefinition(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> workPlanDefinitionRepository.save(order));
        assertEquals(order, workPlanDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> workPlanDefinitionRepository.save(createdElement)));
        assertEquals(order, workPlanDefinitionRepository.findById(testID).get());
        // Read
        assertEquals(workPlanDefinitionRepository.count(), 6);
        assertEquals(order, workPlanDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, workPlanDefinitionRepository.findById(getWithUniqueID(createdElement.getWorkPlanNumber())).get()));
        // Update
        order.setPictureNumber(1L);
        assertDoesNotThrow(() -> workPlanDefinitionRepository.save(order));
        assertEquals(order, workPlanDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setPictureNumber(1L);
            assertDoesNotThrow(() -> workPlanDefinitionRepository.save(createdElement));
            assertEquals(createdElement, workPlanDefinitionRepository.findById(getWithUniqueID(createdElement.getWorkPlanNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> workPlanDefinitionRepository.delete(order));
        workPlanDefinitionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> workPlanDefinitionRepository.delete(element)));
        assertEquals(workPlanDefinitionRepository.count(), 0);
    }
}
