package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Topology;

public class TopologyDTO {
    private ResourceDTO source;
    private ResourceDTO target;
    private Long sourcePosition;
    private Long targetPosition;
    private Long shunt;
    
    public TopologyDTO() {
    }
    
    public TopologyDTO (Topology topology, ResourceDTO source, ResourceDTO target){
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
