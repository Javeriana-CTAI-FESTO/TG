package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.Operation;

public class OperationDTO {

    private Long operationNumber;
    private String description;
    private Long type;
    private String shortDescription;
    private String queryToWrite;
    private String picture;
    private String freeText;

    public OperationDTO(Operation operation) {
        this.operationNumber=operation.getId();
        this.description=operation.getDescription();
        this.type=operation.getType();
        this.shortDescription=operation.getShortDescription();
        this.queryToWrite=operation.getQueryToWrite();
        this.picture=operation.getPicture();
        this.freeText=operation.getFreeText();
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(Long operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getQueryToWrite() {
        return queryToWrite;
    }

    public void setQueryToWrite(String queryToWrite) {
        this.queryToWrite = queryToWrite;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
    
}
