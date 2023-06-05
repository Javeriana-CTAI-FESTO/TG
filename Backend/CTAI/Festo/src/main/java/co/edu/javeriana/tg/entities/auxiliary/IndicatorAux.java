package co.edu.javeriana.tg.entities.auxiliary;

public class IndicatorAux {
    private String indicatorName;
    private String indicatorDescription;
    private Long indicatorValue;
    public IndicatorAux(String indicatorName, String indicatorDescription, Long indicatorValue) {
        this.indicatorName = indicatorName;
        this.indicatorDescription = indicatorDescription;
        this.indicatorValue = indicatorValue;
    }
    public IndicatorAux() {
    }
    public String getIndicatorName() {
        return indicatorName;
    }
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
    public String getIndicatorDescription() {
        return indicatorDescription;
    }
    public void setIndicatorDescription(String indicatorDescription) {
        this.indicatorDescription = indicatorDescription;
    }
    public Long getIndicatorValue() {
        return indicatorValue;
    }
    public void setIndicatorValue(Long indicatorValue) {
        this.indicatorValue = indicatorValue;
    }
}
