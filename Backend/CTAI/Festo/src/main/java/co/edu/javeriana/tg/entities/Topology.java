package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblTopology")
@IdClass(TopologyPK.class)
// Topologias de los modulos (Conexiones entre los modulos)
public class Topology {
    @Id
    @ManyToOne
    @JoinColumn(name = "SourceId")
    private Resource source;
    @ManyToOne
    @JoinColumn(name = "TargetId")
    private Resource target;
    @Id
    @Column(name = "SourcePositionId")
    private Long sourcePosition;
    @Column(name = "TargetPositionId")
    private Long targetPosition;
    @Id
    @Column(name = "ShuntId")
    private Long shunt;
    public Resource getSource() {
        return source;
    }
    public void setSource(Resource source) {
        this.source = source;
    }
    public Resource getTarget() {
        return target;
    }
    public void setTarget(Resource target) {
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
