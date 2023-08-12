package co.edu.javeriana.tg.integration.services;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.managed.Part;
import co.edu.javeriana.tg.repositories.interfaces.PartRepository;
import co.edu.javeriana.tg.services.PartService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class PartServiceTest {
    @Autowired
    private PartService partService;

    @MockBean
    private PartRepository partRepository;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, partService.getAll().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(partRepository.findAll()).thenReturn(List.of(new Part(1L)));
        assertEquals(1, partService.getAll().size());
    }

    @Test
    public void testEmptyGetAllTypes() {
        assertEquals(0, partService.getAllTypes().size());
    }

    @Test
    public void testNonEmptyGetAllTypes() {
        Part p = new Part(1L);
        Long type = 99l;
        p.setType(type);
        when(partRepository.findAll()).thenReturn(List.of(p));
        assertEquals("Undefined", partService.getAllTypes().get(type));
        type = 0l;
        p.setType(type);
        assertEquals("Nothing", partService.getAllTypes().get(type));
        type = 1l;
        p.setType(type);
        assertEquals("Raw Part", partService.getAllTypes().get(type));
        type = 3l;
        p.setType(type);
        assertEquals("Production Part", partService.getAllTypes().get(type));
        type = 9l;
        p.setType(type);
        assertEquals("Box", partService.getAllTypes().get(type));
        type = 10l;
        p.setType(type);
        assertEquals("Carrier", partService.getAllTypes().get(type));
        type = 11l;
        p.setType(type);
        assertEquals("Pallet", partService.getAllTypes().get(type));
        type = 90l;
        p.setType(type);
        assertEquals("Spare Part", partService.getAllTypes().get(type));
        type = 92l;
        p.setType(type);
        assertEquals("", partService.getAllTypes().get(type));
    }

    @Test
    public void testEmptyGetAllByTypes() {
        Long type = 99l;
        assertEquals(0, partService.getAllByType(type).size());
    }

    @Test
    public void testNonEmptyGetAllByTypes() {
        Part p = new Part(1L);
        Long type = 99l;
        p.setType(type);
        when(partRepository.findByType(type)).thenReturn(List.of(p));
        assertEquals(1, partService.getAllByType(type).size());
    }

    @Test
    public void testEmptyCreateOrder() {
        CreatePartAux a = new CreatePartAux();
        assertNull(partService.createPart(a));
    }

    @Test
    public void testNonEmptyCreatePart() {
        CreatePartAux a = new CreatePartAux();
        a.setBasePallet(1l);
        a.setDefaultResourceId(1l);
        a.setDescription("Test");
        a.setLotSize(1l);
        a.setPartNumber(1l);
        a.setMrpType(1l);
        a.setPicture("Test");
        a.setPartNumber(1l);
        a.setSafetyStock(1l);
        a.setType(1l);
        assertNotNull(partService.createPart(a));
    }

    @Test
    public void testNonEmptyCreatePartProd() {
        CreatePartAux a = new CreatePartAux();
        a.setBasePallet(1l);
        a.setDefaultResourceId(1l);
        a.setDescription("Test");
        a.setLotSize(1l);
        a.setPartNumber(1l);
        a.setMrpType(1l);
        a.setPicture("Test");
        a.setPartNumber(1l);
        a.setSafetyStock(1l);
        a.setType(3l);
        assertEquals(a.getWorkPlanNumber(), partService.createPart(a).getWorkPlanNumber());
    }
}
