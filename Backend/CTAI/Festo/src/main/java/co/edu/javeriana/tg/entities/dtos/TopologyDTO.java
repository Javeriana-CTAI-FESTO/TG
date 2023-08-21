package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Topology;
import io.swagger.v3.oas.annotations.media.Schema;

public class TopologyDTO {
    @Schema(name = "source", example = "10", description = "ResourceDTO, recurso utilizado por la maquina", required = true)
    private ResourceDTO source;
    @Schema(name = "target", example = "11", description = "ResourceDTO, recurso utilizado por la maquina", required = true)
    private ResourceDTO target;
    @Schema(name = "sourcePosition", example = "1", description = "Indica la posicion del recurso", required = true)
    private Long sourcePosition;
    @Schema(name = "targetPosition", example = "2", description = "Indica la posicion del recurso", required = true)
    private Long targetPosition;
    @Schema(name = "shunt", example = "1", description = "Indica el shunt", required = true)
    private Long shunt;

    public TopologyDTO() {
    }

    public TopologyDTO(Topology topology, ResourceDTO source, ResourceDTO target) {
        this.source = source;
        this.target = target;
        this.sourcePosition = topology.getSourcePosition();
        this.targetPosition = topology.getTargetPosition();
        this.shunt = topology.getShunt();
    }

    public ResourceDTO getSource() {
        return source;
    }

    public void setSource(ResourceDTO source) {
        this.source = source;
    }

    public ResourceDTO getTarget() {
        return target;
    }

    public void setTarget(ResourceDTO target) {
        this.target = target;
    }

    public Long getSourcePosition() {
        return sourcePosition;
    }

    public void setSourcePosition(Long sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public Long getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Long targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Long getShunt() {
        return shunt;
    }

    public void setShunt(Long shunt) {
        this.shunt = shunt;
    }
}
