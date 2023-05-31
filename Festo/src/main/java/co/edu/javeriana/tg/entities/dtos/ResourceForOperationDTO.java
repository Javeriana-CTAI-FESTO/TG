package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

public class ResourceForOperationDTO {
    private OperationDTO operation;
    private List<ResourceDTO> resources;

    public ResourceForOperationDTO(){}
    
    public ResourceForOperationDTO(OperationDTO operation, List<ResourceDTO> resources){
        this.operation = operation;
        this.resources = resources;
    }

    public OperationDTO getOperation() {
        return operation;
    }
    public void setOperation(OperationDTO operation) {
        this.operation = operation;
    }
    public List<ResourceDTO> getResources() {
        return resources;
    }
    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
    
}
