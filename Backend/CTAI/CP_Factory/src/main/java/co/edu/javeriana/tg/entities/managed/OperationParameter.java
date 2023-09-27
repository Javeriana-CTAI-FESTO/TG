package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblOperationParameter")
@IdClass(OperationParameterPK.class)
public class OperationParameter {
  @Id
  @Column(name = "OpNo")
  private Long operationNumber;
  @Id
  @Column(name = "ParameterNo")
  private Long parameterNumber;
  @Column(name = "Description")
  private String description;
  @Column(name = "DataType")
  private Long dataType;
  @Column(name = "LowLimit")
  private Long lowLimit;
  @Column(name = "HighLimit")
  private Long highLimit;
  @Column(name = "ParameterType")
  private Long parameterType;
  @Column(name = "Parameter")
  private String parameter;
  @Column(name = "QuerryChooseParameter")
  private String queryChooseParameter;
  @Column(name = "ValueType")
  private Long valueType;
  @Column(name = "StringLength")
  private Long stringLength;

  public OperationParameter() {
  }

  public OperationParameter(Long operationNumber, Long parameterNumber, String description, Long dataType,
      Long lowLimit, Long highLimit, Long parameterType, String parameter, String queryChooseParameter, Long valueType,
      Long stringLength) {
    this.operationNumber = operationNumber;
    this.parameterNumber = parameterNumber;
    this.description = description;
    this.dataType = dataType;
    this.lowLimit = lowLimit;
    this.highLimit = highLimit;
    this.parameterType = parameterType;
    this.parameter = parameter;
    this.queryChooseParameter = queryChooseParameter;
    this.valueType = valueType;
    this.stringLength = stringLength;
  }

  public Long getOperationNumber() {
    return operationNumber;
  }

  public void setOperationNumber(Long operationNumber) {
    this.operationNumber = operationNumber;
  }

  public Long getParameterNumber() {
    return parameterNumber;
  }

  public void setParameterNumber(Long parameterNumber) {
    this.parameterNumber = parameterNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getDataType() {
    return dataType;
  }

  public void setDataType(Long dataType) {
    this.dataType = dataType;
  }

  public Long getLowLimit() {
    return lowLimit;
  }

  public void setLowLimit(Long lowLimit) {
    this.lowLimit = lowLimit;
  }

  public Long getHighLimit() {
    return highLimit;
  }

  public void setHighLimit(Long highLimit) {
    this.highLimit = highLimit;
  }

  public Long getParameterType() {
    return parameterType;
  }

  public void setParameterType(Long parameterType) {
    this.parameterType = parameterType;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getQueryChooseParameter() {
    return queryChooseParameter;
  }

  public void setQueryChooseParameter(String queryChooseParameter) {
    this.queryChooseParameter = queryChooseParameter;
  }

  public Long getValueType() {
    return valueType;
  }

  public void setValueType(Long valueType) {
    this.valueType = valueType;
  }

  public Long getStringLength() {
    return stringLength;
  }

  public void setStringLength(Long stringLength) {
    this.stringLength = stringLength;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((operationNumber == null) ? 0 : operationNumber.hashCode());
    result = prime * result + ((parameterNumber == null) ? 0 : parameterNumber.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
    result = prime * result + ((lowLimit == null) ? 0 : lowLimit.hashCode());
    result = prime * result + ((highLimit == null) ? 0 : highLimit.hashCode());
    result = prime * result + ((parameterType == null) ? 0 : parameterType.hashCode());
    result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
    result = prime * result + ((queryChooseParameter == null) ? 0 : queryChooseParameter.hashCode());
    result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
    result = prime * result + ((stringLength == null) ? 0 : stringLength.hashCode());
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
    OperationParameter other = (OperationParameter) obj;
    if (operationNumber == null) {
      if (other.operationNumber != null)
        return false;
    } else if (!operationNumber.equals(other.operationNumber))
      return false;
    if (parameterNumber == null) {
      if (other.parameterNumber != null)
        return false;
    } else if (!parameterNumber.equals(other.parameterNumber))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (dataType == null) {
      if (other.dataType != null)
        return false;
    } else if (!dataType.equals(other.dataType))
      return false;
    if (lowLimit == null) {
      if (other.lowLimit != null)
        return false;
    } else if (!lowLimit.equals(other.lowLimit))
      return false;
    if (highLimit == null) {
      if (other.highLimit != null)
        return false;
    } else if (!highLimit.equals(other.highLimit))
      return false;
    if (parameterType == null) {
      if (other.parameterType != null)
        return false;
    } else if (!parameterType.equals(other.parameterType))
      return false;
    if (parameter == null) {
      if (other.parameter != null)
        return false;
    } else if (!parameter.equals(other.parameter))
      return false;
    if (queryChooseParameter == null) {
      if (other.queryChooseParameter != null)
        return false;
    } else if (!queryChooseParameter.equals(other.queryChooseParameter))
      return false;
    if (valueType == null) {
      if (other.valueType != null)
        return false;
    } else if (!valueType.equals(other.valueType))
      return false;
    if (stringLength == null) {
      if (other.stringLength != null)
        return false;
    } else if (!stringLength.equals(other.stringLength))
      return false;
    return true;
  }
}
