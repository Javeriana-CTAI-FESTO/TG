package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class PartsConsumedByOrderDTO {

  public PartsConsumedByOrderDTO(OrderDTO order, List<PartDTO> consumedParts) {
    this.order = order;
    this.consumedParts = consumedParts;
  }

  @Schema(name = "order", example = "110", description = "OrderDTO seleccionado", required = true)
  private OrderDTO order;
  @Schema(name = "consumedParts", example = "10", description = "Lista de partes asignados a la orden", required = true)
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
