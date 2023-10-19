package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResourceForOperationDTO {
    @Schema(name = "operation", example = "110", description = "OperationDTO seleccionada", required = true)
    private OperationDTO operation;
    @Schema(name = "resources", example = "10", description = "Lista de recursos asignados a la operación", required = true)
    private List<ResourceDTO> resources;

    public ResourceForOperationDTO() {
    }

    public ResourceForOperationDTO(OperationDTO operation, List<ResourceDTO> resources) {
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
