package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.Error;

public class ErrorDTO {
    private Long id;
    private String description;
    private String shortDescription;

    public ErrorDTO() {
    }

    public ErrorDTO(Error error) {
        this.id = error.getId();
        this.description = error.getDescription();
        this.shortDescription = error.getShortDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
