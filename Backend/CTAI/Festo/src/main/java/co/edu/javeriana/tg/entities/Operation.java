package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblOperation")
public class Operation {

    @Id
    @Column(name = "OPNo")
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
}