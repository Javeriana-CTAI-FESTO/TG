package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

public class PartsConsumedByOrderDTO {
  
  public PartsConsumedByOrderDTO(OrderDTO order, List<PartDTO> consumedParts) {
    this.order = order;
    this.consumedParts = consumedParts;
  }
  private OrderDTO order;
  private List<PartDTO> consumedParts;
  
  public PartsConsumedByOrderDTO() {
  }
  public OrderDTO getOrder() {
    return order;
  }
  public void setOrder(OrderDTO order) {
    this.order = order;
  }
  public List<PartDTO> getConsumedParts() {
    return consumedParts;
  }
  public void setConsumedParts(List<PartDTO> consumedParts) {
    this.consumedParts = consumedParts;
  }
}
