package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblBuffer")
@IdClass(BufferPK.class)
// Almacenamiento asociado a un recurso
public class Buffer {

    @Id
    @ManyToOne
    @JoinColumn(name = "ResourceId")
    private Resource resource;
    @Id
    @Column(name = "BufNo")
    private Long bufferNumber;
    @Column(name = "Description")
    private String description;
    @Column(name = "Type")
    private Long type;
    @Column(name = "BeltNo")
    private Long beltNumber;
    @Column(name = "Sides")
    private Long sides;
    @Column(name = "Rows")
    private Long rows;
    @Column(name = "Columns")
    private Long columns;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
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
