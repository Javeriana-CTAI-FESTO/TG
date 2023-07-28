package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblTopology")
@IdClass(TopologyPK.class)
// Topologias de los modulos (Conexiones entre los modulos)
public class Topology {
    @Id
    @Column(name = "SourceId", nullable = false)
    private Long source;
    @Column(name = "TargetId")
    private Long target;
    @Id
    @Column(name = "SourcePositionId", nullable = false)
    private Long sourcePosition;
    @Column(name = "TargetPositionId")
    private Long targetPosition;
    @Id
    @Column(name = "ShuntId", nullable = false)
    private Long shunt;
    public Long getSource() {
        return source;
    }
    public void setSource(Long source) {
        this.source = source;
    }
    public Long getTarget() {
        return target;
    }
    public void setTarget(Long target) {
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
