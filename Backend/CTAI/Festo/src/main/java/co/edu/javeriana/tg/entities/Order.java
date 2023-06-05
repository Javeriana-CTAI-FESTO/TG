package co.edu.javeriana.tg.entities;

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
    @Column(name = "ONo")
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
}
