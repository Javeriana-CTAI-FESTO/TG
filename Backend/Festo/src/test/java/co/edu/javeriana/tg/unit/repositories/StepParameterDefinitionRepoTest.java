package co.edu.javeriana.tg.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

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

import co.edu.javeriana.tg.entities.managed.StepParameterDefinition;
import co.edu.javeriana.tg.entities.managed.StepParameterDefinitionPK;
import co.edu.javeriana.tg.repositories.interfaces.StepParameterDefinitionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StepParameterDefinitionRepoTest {
    @Autowired
    StepParameterDefinitionRepository stepParameterDefinitionRepository;

    private StepParameterDefinitionPK testID = getWithUniqueID(1L);

    private List<StepParameterDefinitionPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private StepParameterDefinitionPK getWithUniqueID(Long id){
        return new StepParameterDefinitionPK(id, id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        StepParameterDefinition order = new StepParameterDefinition(testID);
        List<StepParameterDefinition> createdList = idsList.stream().map(id -> new StepParameterDefinition(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> stepParameterDefinitionRepository.save(order));
        assertEquals(order, stepParameterDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> stepParameterDefinitionRepository.save(createdElement)));
        assertEquals(order, stepParameterDefinitionRepository.findById(testID).get());
        // Read
        assertEquals(stepParameterDefinitionRepository.count(), 6);
        assertEquals(order, stepParameterDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, stepParameterDefinitionRepository.findById(getWithUniqueID(createdElement.getRelatedWorkPlan())).get()));
        // Update
        order.setParameter("Name");
        assertDoesNotThrow(() -> stepParameterDefinitionRepository.save(order));
        assertEquals(order, stepParameterDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setParameter("Name");
            assertDoesNotThrow(() -> stepParameterDefinitionRepository.save(createdElement));
            assertEquals(createdElement, stepParameterDefinitionRepository.findById(getWithUniqueID(createdElement.getRelatedWorkPlan())).get());
        });
        // Delete
        assertDoesNotThrow(() -> stepParameterDefinitionRepository.delete(order));
        stepParameterDefinitionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> stepParameterDefinitionRepository.delete(element)));
        assertEquals(stepParameterDefinitionRepository.count(), 0);
    }
}
