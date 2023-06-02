package co.edu.javeriana.tg.entities;

import java.io.Serializable;

public class TopologyPK implements Serializable{
    private Long source;
    private Long sourcePosition;
    private Long shunt;
    public TopologyPK(Long source, Long sourcePosition, Long shunt) {
        this.source = source;
        this.sourcePosition = sourcePosition;
        this.shunt = shunt;
    }
    public TopologyPK() {
    }
    public Long getSource() {
        return source;
    }
    public void setSource(Long source) {
        this.source = source;
    }
    public Long getSourcePosition() {
        return sourcePosition;
    }
    public void setSourcePosition(Long sourcePosition) {
        this.sourcePosition = sourcePosition;
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
        result = prime * result + ((sourcePosition == null) ? 0 : sourcePosition.hashCode());
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
        TopologyPK other = (TopologyPK) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (sourcePosition == null) {
            if (other.sourcePosition != null)
                return false;
        } else if (!sourcePosition.equals(other.sourcePosition))
            return false;
        if (shunt == null) {
            if (other.shunt != null)
                return false;
        } else if (!shunt.equals(other.shunt))
            return false;
        return true;
    }
}
