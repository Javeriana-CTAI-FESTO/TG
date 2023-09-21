package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblBuffer")
@IdClass(value = BufferPK.class)
// Almacenamiento asociado a un recurso
public class Buffer {

    @Id
    @Column(name = "ResourceId", nullable = false)
    private Long resource;
    @Id
    @Column(name = "BufNo", nullable = false)
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

    public Buffer() {
    }

    public Buffer(BufferPK testID) {
        this.resource = testID.getResource();
        this.bufferNumber=testID.getBufferNumber();
    }

    public Long getResource() {
        return resource;
    }

    public void setResource(Long resource) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((bufferNumber == null) ? 0 : bufferNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((beltNumber == null) ? 0 : beltNumber.hashCode());
        result = prime * result + ((sides == null) ? 0 : sides.hashCode());
        result = prime * result + ((rows == null) ? 0 : rows.hashCode());
        result = prime * result + ((columns == null) ? 0 : columns.hashCode());
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
        Buffer other = (Buffer) obj;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (bufferNumber == null) {
            if (other.bufferNumber != null)
                return false;
        } else if (!bufferNumber.equals(other.bufferNumber))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (beltNumber == null) {
            if (other.beltNumber != null)
                return false;
        } else if (!beltNumber.equals(other.beltNumber))
            return false;
        if (sides == null) {
            if (other.sides != null)
                return false;
        } else if (!sides.equals(other.sides))
            return false;
        if (rows == null) {
            if (other.rows != null)
                return false;
        } else if (!rows.equals(other.rows))
            return false;
        if (columns == null) {
            if (other.columns != null)
                return false;
        } else if (!columns.equals(other.columns))
            return false;
        return true;
    }
}
