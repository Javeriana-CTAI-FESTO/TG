package co.edu.javeriana.tg.entities;

import java.io.Serializable;

public class OrderPositionPK implements Serializable {
    private Long order;
    private Long orderPosition;
    public OrderPositionPK() {
    }
    public OrderPositionPK(Long orderNumber, Long orderPosition) {
        this.order = orderNumber;
        this.orderPosition = orderPosition;
    }
    public Long getOrder() {
        return order;
    }
    public void setOrder(Long orderNumber) {
        this.order = orderNumber;
    }
    public Long getOrderPosition() {
        return orderPosition;
    }
    public void setOrderPosition(Long orderPosition) {
        this.orderPosition = orderPosition;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((orderPosition == null) ? 0 : orderPosition.hashCode());
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
        OrderPositionPK other = (OrderPositionPK) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (orderPosition == null) {
            if (other.orderPosition != null)
                return false;
        } else if (!orderPosition.equals(other.orderPosition))
            return false;
        return true;
    }
}
