package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.Part;
import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.repositories.PartRepository;

@Service
public class PartService {
    
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<PartDTO> getAll() {
        return partRepository.findAll().stream().map(PartDTO::new).collect(Collectors.toList());
    }

    private String getTypeDescription(Long long1) {
        String retorno = "";
        if (long1 == 0)
            retorno = "Nothing";
        else if (long1 == 99)
            retorno = "Part";
        else if (long1 == 1)
            retorno = "Raw";
        else if (long1 == 3)
            retorno = "Production";
        else if (long1 == 9)
            retorno = "Part";
        else if (long1 == 11)
            retorno = "Part";
        else if (long1 == 90)
            retorno = "Part";
        return retorno;
    }

    public Map<Long, String> getAllTypes() {
        return partRepository.findAll().stream()
                .collect(Collectors.toMap(Part::getType, p -> this.getTypeDescription(p.getType())));
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
}
