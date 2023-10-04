package co.edu.javeriana.tg.entities.dtos;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class StepTimeDTO {
  private StepDefinitionDTO step;
  private ZonedDateTime start;
  private ZonedDateTime end;

  public StepTimeDTO(StepDefinitionDTO step, Date start, Date end) {
    this.step = step;
    this.start = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("America/Bogota"));
    this.end = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.of("America/Bogota"));
  }

  public StepTimeDTO() {
  }

  public StepDefinitionDTO getStep() {
    return step;
  }

  public void setStep(StepDefinitionDTO step) {
    this.step = step;
  }

  public ZonedDateTime getStart() {
    return start;
  }

  public void setStart(ZonedDateTime start) {
    this.start = start;
  }

  public ZonedDateTime getEnd() {
    return end;
  }

  public void setEnd(ZonedDateTime end) {
    this.end = end;
  }
}
