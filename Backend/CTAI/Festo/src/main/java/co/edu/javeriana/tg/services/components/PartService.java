package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.managed.Part;
import co.edu.javeriana.tg.repositories.interfaces.PartRepository;

@Component
@Transactional
public class PartService {

    private final PartRepository partRepository;
    private final WorkPlanService workPlanService;

    public PartService(PartRepository partRepository, WorkPlanService workPlanService) {
        this.partRepository = partRepository;
        this.workPlanService = workPlanService;
    }

    public List<PartDTO> getAll() {
        return partRepository.findAll().stream().map(PartDTO::new).collect(Collectors.toList());
    }

    public List<PartDTO> getAllAvailable() {
        return partRepository.findAllAvailable().stream().map(PartDTO::new).collect(Collectors.toList());
    }

    public List<PartDTO> getAllUnavailable() {
        return partRepository.findAllUnavailable().stream().map(PartDTO::new).collect(Collectors.toList());
    }

    private String getTypeDescription(Long typeNumber) {
        String retorno = "";
        if (typeNumber == 0)
            retorno = "Nothing";
        else if (typeNumber == 99)
            retorno = "Undefined";
        else if (typeNumber == 1)
            retorno = "Raw Part";
        else if (typeNumber == 3)
            retorno = "Production Part";
        else if (typeNumber == 9)
            retorno = "Box";
        else if (typeNumber == 10)
            retorno = "Carrier";
        else if (typeNumber == 11)
            retorno = "Pallet";
        else if (typeNumber == 90)
            retorno = "Spare Part";
        return retorno;
    }

    public Map<Long, String> getAllTypes() {
        return partRepository.findAllTypes().stream()
                .collect(Collectors.toMap(type -> type, type -> this.getTypeDescription(type)));
    }

    public List<PartDTO> getAllByType(Long typeId) {
        return partRepository.findByType(typeId).stream().map(PartDTO::new).collect(Collectors.toList());
    }

    public PartDTO createPart(CreatePartAux createRequest) {
        PartDTO createdPart = null;
        try {
            Part part = new Part();
            part.setBasePallet(createRequest.getBasePallet());
            part.setDefaultResourceId(createRequest.getDefaultResourceId());
            part.setDescription(createRequest.getDescription());
            part.setLotSize(createRequest.getLotSize());
            part.setPartNumber(createRequest.getPartNumber());
            part.setMrpType(createRequest.getMrpType());
            part.setPicture(createRequest.getPicture());
            part.setPartNumber(createRequest.getPartNumber());
            part.setSafetyStock(createRequest.getSafetyStock());
            part.setType(createRequest.getType());
            if (createRequest.getType() == 3)
                part.setWorkPlanNumber(createRequest.getWorkPlanNumber());
            createdPart = new PartDTO(part);
        } catch (Exception e) {
        }
        return createdPart;
    }

    public Long getWorkPlanNumberByPart(Long partNumber) {
        try {
            return partRepository.findById(partNumber).get().getWorkPlanNumber();
        } catch (Exception e) {

        }
        return null;
    }

    public List<PartDTO> getAllProductionProduceableParts() {
        return partRepository.getAllProductionParts().stream().filter(part -> workPlanService.getWorkPlanTypeByWorkPlanNumber(part.getWorkPlanNumber())==1).map(PartDTO::new).collect(Collectors.toList());
    }

    public List<PartDTO> getAllCustomerProduceableParts() {
        return partRepository.getAllProductionParts().stream().filter(part -> workPlanService.getWorkPlanTypeByWorkPlanNumber(part.getWorkPlanNumber())==2).map(PartDTO::new).collect(Collectors.toList());
    }

    public List<PartDTO> getPartsThatCanBeProduced() {
        List<PartDTO> produceableParts = this.getAllProductionProduceableParts();
        produceableParts.addAll(this.getAllCustomerProduceableParts());
        return produceableParts;
      }

    

}
