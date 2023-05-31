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
}
