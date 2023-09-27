package co.edu.javeriana.tg.entities.dtos;

import java.util.Date;

public class StepTimeDTO {
  private StepDefinitionDTO step;
  private Date start;
  private Date end;

  public StepTimeDTO(StepDefinitionDTO step, Date start, Date end) {
    this.step = step;
    this.start = start;
    this.end = end;
  }

  public StepTimeDTO() {
  }

  public StepDefinitionDTO getStep() {
    return step;
  }

  public void setStep(StepDefinitionDTO step) {
    this.step = step;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }
}
