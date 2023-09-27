package co.edu.javeriana.tg.entities.managed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblOrder")
// Ordenes pendientes por ser procesadas
public class Order {
    @Id
    @Column(name = "ONo", nullable = false)
    private Long orderNumber;
    @Column(name = "PlanedStart")
    private Date plannedStart;
    @Column(name = "PlanedEnd")
    private Date plannedEnd;
    @Column(name = "Start")
    private Date realStart;
    @Column(name = "End")
    private Date realEnd;
    @Column(name = "State")
    private Long state;
    @Column(name = "CNo")
    private Long clientNumber;
    @Column(name = "Enabled")
    private Boolean enabled;
    @Column(name = "Release")
    private Date release;
    public Order() {
    }
    public Order(Long testID) {
        this.orderNumber = testID;
    }
    public Long getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Date getPlannedStart() {
        return plannedStart;
    }
    public void setPlannedStart(Date plannedStart) {
        this.plannedStart = plannedStart;
    }
    public Date getPlannedEnd() {
        return plannedEnd;
    }
    public void setPlannedEnd(Date plannedEnd) {
        this.plannedEnd = plannedEnd;
    }
    public Date getRealStart() {
        return realStart;
    }
    public void setRealStart(Date realStart) {
        this.realStart = realStart;
    }
    public Date getRealEnd() {
        return realEnd;
    }
    public void setRealEnd(Date realEnd) {
        this.realEnd = realEnd;
    }
    public Long getState() {
        return state;
    }
    public void setState(Long state) {
        this.state = state;
    }
    public Long getClientNumber() {
        return clientNumber;
    }
    public void setClientNumber(Long cNumber) {
        this.clientNumber = cNumber;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Date getRelease() {
        return release;
    }
    public void setRelease(Date release) {
        this.release = release;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + ((plannedStart == null) ? 0 : plannedStart.hashCode());
        result = prime * result + ((plannedEnd == null) ? 0 : plannedEnd.hashCode());
        result = prime * result + ((realStart == null) ? 0 : realStart.hashCode());
        result = prime * result + ((realEnd == null) ? 0 : realEnd.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((clientNumber == null) ? 0 : clientNumber.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result + ((release == null) ? 0 : release.hashCode());
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
        Order other = (Order) obj;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        if (plannedStart == null) {
            if (other.plannedStart != null)
                return false;
        } else if (!plannedStart.equals(other.plannedStart))
            return false;
        if (plannedEnd == null) {
            if (other.plannedEnd != null)
                return false;
        } else if (!plannedEnd.equals(other.plannedEnd))
            return false;
        if (realStart == null) {
            if (other.realStart != null)
                return false;
        } else if (!realStart.equals(other.realStart))
            return false;
        if (realEnd == null) {
            if (other.realEnd != null)
                return false;
        } else if (!realEnd.equals(other.realEnd))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (clientNumber == null) {
            if (other.clientNumber != null)
                return false;
        } else if (!clientNumber.equals(other.clientNumber))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        if (release == null) {
            if (other.release != null)
                return false;
        } else if (!release.equals(other.release))
            return false;
        return true;
    }
}
