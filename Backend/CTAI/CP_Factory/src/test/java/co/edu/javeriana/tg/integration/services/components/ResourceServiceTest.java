package co.edu.javeriana.tg.integration.services.components;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;
import co.edu.javeriana.tg.services.components.ResourceService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class ResourceServiceTest {
    @Autowired
    private ResourceService resourceService;
    @MockBean
    private ResourceRepository resourceRepository;
    @Test
    public void testEmptyGetAll() {
        assertEquals(0, resourceService.getAllResources().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(resourceRepository.findAll()).thenReturn(List.of(new Resource(1L)));
        assertEquals(1, resourceService.getAllResources().size());
    }

    @Test
    public void testEmptyGetById() {
        Long resource = 1L;
        assertNull(resourceService.getResourceById(resource));
    }

    @Test
    public void testNonEmptyGetById() {
        Long resource = 1L;
        when(resourceRepository.findById(resource)).thenReturn(Optional.of(new Resource(resource)));
        assertNotNull(resourceService.getResourceById(resource));
    }
}