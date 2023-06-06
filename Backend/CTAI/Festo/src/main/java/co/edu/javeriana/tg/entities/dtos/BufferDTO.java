package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.Buffer;

public class BufferDTO {
    private ResourceDTO resource;
    private Long bufferNumber;
    private String description;
    private Long type;
    private Long beltNumber;
    private Long sides;
    private Long rows;
    private Long columns;

    public BufferDTO() {
    }

    public BufferDTO(Buffer buffer) {
        this.resource = new ResourceDTO(buffer.getResource());
        this.bufferNumber = buffer.getBufferNumber();
        this.description = buffer.getDescription();
        this.type = buffer.getType();
        this.beltNumber = buffer.getBeltNumber();
        this.sides = buffer.getSides();
        this.rows = buffer.getRows();
        this.columns = buffer.getColumns();
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    public Long getBufferNumber() {
        return bufferNumber;
    }

    public void setBufferNumber(Long bufferNumber) {
        this.bufferNumber = bufferNumber;
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

    public Long getBeltNumber() {
        return beltNumber;
    }

    public void setBeltNumber(Long beltNumber) {
        this.beltNumber = beltNumber;
    }

    public Long getSides() {
        return sides;
    }

    public void setSides(Long sides) {
        this.sides = sides;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Long getColumns() {
        return columns;
    }

    public void setColumns(Long columns) {
        this.columns = columns;
    }
}
