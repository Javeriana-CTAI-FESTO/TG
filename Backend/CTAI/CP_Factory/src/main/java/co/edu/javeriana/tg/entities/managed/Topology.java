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
    public Topology() {
    }
    public Topology(TopologyPK id) {
        this.source = id.getSource();
        this.shunt = id.getShunt();
        this.sourcePosition = id.getSourcePosition();
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        result = prime * result + ((sourcePosition == null) ? 0 : sourcePosition.hashCode());
        result = prime * result + ((targetPosition == null) ? 0 : targetPosition.hashCode());
        result = prime * result + ((shunt == null) ? 0 : shunt.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topology other = (Topology) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        if (sourcePosition == null) {
            if (other.sourcePosition != null)
                return false;
        } else if (!sourcePosition.equals(other.sourcePosition))
            return false;
        if (targetPosition == null) {
            if (other.targetPosition != null)
                return false;
        } else if (!targetPosition.equals(other.targetPosition))
            return false;
        if (shunt == null) {
            if (other.shunt != null)
                return false;
        } else if (!shunt.equals(other.shunt))
            return false;
        return true;
    }
}
