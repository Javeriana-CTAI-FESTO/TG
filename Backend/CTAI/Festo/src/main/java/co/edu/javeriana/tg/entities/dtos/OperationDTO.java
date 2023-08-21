package co.edu.javeriana.tg.entities.dtos;

import javax.persistence.SecondaryTable;

import co.edu.javeriana.tg.entities.managed.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

public class OperationDTO {

    @Schema(name = "operationNumber", example = "110", description = "Numero que representa una operacion en el sistema", required = true)
    private Long operationNumber;
    @Schema(name = "description", example = "pressing for time in seconds", description = "Descripción de la operación", required = true)
    private String description;
    @Schema(name = "type", example = "1", description = "Indica el tipo de operación", required = true)
    private Long type;
    @Schema(name = "shortDescription", example = "pressing", description = "Descripción corta de la operación", required = true)
    private String shortDescription;
    @Schema(name = "queryToWrite", example = "UPDATE \n" + //
            "tblBoxPos \n" + //
            "SET \n" + //
            "tblBoxPos.ONo = 0, \n" + //
            "tblBoxPos.OPos = 0,\n" + //
            "tblBoxPos.PNo = 0 \n" + //
            "WHERE \n" + //
            "tblBoxPos.ONo = #ONo AND \n" + //
            "tblBoxPos.OPos = #OPos", description = "Indicación e instrucción SQL de la operación", required = true)
    private String queryToWrite;
    @Schema(name = "picture", example = "imagen.png", description = "Imagen de la operación", required = false)
    private String picture;
    @Schema(name = "freeText", example = "free text", description = "Campo libre de texto (comentarios)", required = true)
    private String freeText;

    public OperationDTO(Operation operation) {
        this.operationNumber = operation.getId();
        this.description = operation.getDescription();
        this.type = operation.getType();
        this.shortDescription = operation.getShortDescription();
        this.queryToWrite = operation.getQueryToWrite();
        this.picture = operation.getPicture();
        this.freeText = operation.getFreeText();
    }

    public OperationDTO() {
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
