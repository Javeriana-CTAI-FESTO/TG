package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblOperation")
// Operaciones que pueden llegar a realizarse
public class Operation {

    @Id
    @Column(name = "OPNo", nullable = false)
    private Long id;
    @Column(name = "Description")
    private String description;
    @Column(name = "Type")
    private Long type;
    @Column(name = "Short")
    private String shortDescription;
    @Column(name = "SqlWrite")
    private String queryToWrite;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "String")
    private String freeText;

    public Operation() {
    }

    public Operation(Long testID) {
        this.id = testID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long workPlan) {
        this.id = workPlan;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String pictureNumber) {
        this.picture = pictureNumber;
    }

    public String getQueryToWrite() {
        return queryToWrite;
    }

    public void setQueryToWrite(String queryToWrite) {
        this.queryToWrite = queryToWrite;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
        result = prime * result + ((queryToWrite == null) ? 0 : queryToWrite.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((freeText == null) ? 0 : freeText.hashCode());
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
        Operation other = (Operation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (shortDescription == null) {
            if (other.shortDescription != null)
                return false;
        } else if (!shortDescription.equals(other.shortDescription))
            return false;
        if (queryToWrite == null) {
            if (other.queryToWrite != null)
                return false;
        } else if (!queryToWrite.equals(other.queryToWrite))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (freeText == null) {
            if (other.freeText != null)
                return false;
        } else if (!freeText.equals(other.freeText))
            return false;
        return true;
    }
}