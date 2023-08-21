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

import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.managed.Part;
import co.edu.javeriana.tg.repositories.interfaces.PartRepository;
import co.edu.javeriana.tg.services.components.PartService;

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
        assertEquals(0, partService.getAllAvailable().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(partRepository.findAll()).thenReturn(List.of(new Part(1L)));
        assertEquals(1, partService.getAll().size());
    }

    @Test
    public void testEmptyGetUnavailable() {
        assertEquals(0, partService.getAllUnavailable().size());
    }

    @Test
    public void testNonEmptyGetUnavailable() {
        when(partRepository.findAllUnavailable()).thenReturn(List.of(new Part(1L)));
        assertEquals(1, partService.getAllUnavailable().size());
    }

    @Test
    public void testEmptyGetWorkPlanNumberByPart() {
        assertNull(partService.getWorkPlanNumberByPart(1l));
    }

    @Test
    public void testNonEmptyGetWorkPlanNumberByPart() {
        Part p = new Part();
        p.setPartNumber(1l);
        p.setWorkPlanNumber(1l);
        when(partRepository.findById(1l)).thenReturn(Optional.of(p));
        assertEquals(p.getWorkPlanNumber(), partService.getWorkPlanNumberByPart(p.getPartNumber()));
    }

    @Test
    public void testEmptyGetAllTypes() {
        assertEquals(0, partService.getAllTypes().size());
    }

    @Test
    public void testNonEmptyGetAllTypes() {
        Long type = 99l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Undefined", partService.getAllTypes().get(type));
        type = 0l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Nothing", partService.getAllTypes().get(type));
        type = 1l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Raw Part", partService.getAllTypes().get(type));
        type = 3l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Production Part", partService.getAllTypes().get(type));
        type = 9l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Box", partService.getAllTypes().get(type));
        type = 10l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Carrier", partService.getAllTypes().get(type));
        type = 11l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Pallet", partService.getAllTypes().get(type));
        type = 90l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
        assertEquals("Spare Part", partService.getAllTypes().get(type));
        type = 92l;
        when(partRepository.findAllTypes()).thenReturn(List.of(type));
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

    @Test
    public void testProductionProduceableParts(){
        assertDoesNotThrow(() -> partService.getAllProductionProduceableParts());
    }

    @Test
    public void testCustomerProduceableParts(){
        assertDoesNotThrow(() -> partService.getAllCustomerProduceableParts());
    }

    public void testProduceableParts(){
        assertDoesNotThrow(() -> partService.getPartsThatCanBeProduced());
    }
}
