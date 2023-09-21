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

import co.edu.javeriana.tg.entities.managed.Topology;
import co.edu.javeriana.tg.entities.managed.TopologyPK;
import co.edu.javeriana.tg.repositories.interfaces.TopologyRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TopologyRepoTest{
    @Autowired
    TopologyRepository topologyRepository;

    private TopologyPK testID = getWithUniqueID(1L);

    private List<TopologyPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private TopologyPK getWithUniqueID(Long id){
        return new TopologyPK(id, id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        Topology order = new Topology(testID);
        List<Topology> createdList = idsList.stream().map(id -> new Topology(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> topologyRepository.save(order));
        assertEquals(order, topologyRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> topologyRepository.save(createdElement)));
        assertEquals(order, topologyRepository.findById(testID).get());
        // Read
        assertEquals(topologyRepository.count(), 6);
        assertEquals(order, topologyRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, topologyRepository.findById(getWithUniqueID(createdElement.getSourcePosition())).get()));
        // Update
        order.setTargetPosition(1L);
        assertDoesNotThrow(() -> topologyRepository.save(order));
        assertEquals(order, topologyRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setTargetPosition(1L);
            assertDoesNotThrow(() -> topologyRepository.save(createdElement));
            assertEquals(createdElement, topologyRepository.findById(getWithUniqueID(createdElement.getSourcePosition())).get());
        });
        // Delete
        assertDoesNotThrow(() -> topologyRepository.delete(order));
        topologyRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> topologyRepository.delete(element)));
        assertEquals(topologyRepository.count(), 0);
    }
}
