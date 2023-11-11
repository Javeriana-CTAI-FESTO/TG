package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Error;
import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorDTO {
    @Schema(name = "id", example = "1", description = "Identificador del error", required = true)
    private Long id;
    @Schema(name = "description", example = "Error", description = "Descripción del error", required = true)
    private String description;
    @Schema(name = "shortDescription", example = "Error", description = "Descripción corta del error", required = true)
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
