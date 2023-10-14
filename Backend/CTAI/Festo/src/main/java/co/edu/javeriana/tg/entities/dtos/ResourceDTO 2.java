package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Resource;
import io.swagger.v3.oas.annotations.media.Schema;

public class ResourceDTO {
    @Schema(name = "id", example = "0125", description = "Numero de identificación para un recurso", required = true)
    private Long id;
    @Schema(name = "name", example = "Recurso 1", description = "Nombre que se identifica un recurso", required = true)
    private String name;
    @Schema(name = "description", example = "Placa metalica de ensamblaje", description = "Descripción del recurso, funcionalidad, material", required = true)
    private String description;
    @Schema(name = "plcType", example = "1", description = "plcType", required = true)
    private Long plcType;
    @Schema(name = "IP", example = "89.207.132.170", description = "Dirección IP", required = true)
    private String ip;
    @Schema(name = "picture", example = "imagen.png", description = "Imagen del recurso", required = true)
    private String picture;
    @Schema(name = "parallelProcessing", example = "false", description = "Indica si el recurso es paralelo", required = true)
    private Boolean parallelProcessing;
    @Schema(name = "automatic", example = "false", description = "Indica si el recurso es automático", required = true)
    private Boolean automatic;
    @Schema(name = "webPage", example = "172.17.0.1", description = "Web page del recurso", required = true)
    private String webPage;
    @Schema(name = "openWithDefaultBrowser", example = "true", description = "Indica si el recurso abre con el navegador por defecto", required = true)
    private Boolean openWithDefaultBrowser;
    @Schema(name = "topologyType", example = "1", description = "Indica el tipo de topología", required = true)
    private Long topologyType;
    @Schema(name = "agv", example = "false", description = "Indica si el recurso es agv", required = true)
    private Boolean agv;
    @Schema(name = "resourceType", example = "1", description = "Indica el tipo de recurso", required = true)
    private Long resourceType;

    public ResourceDTO() {
    }

    public ResourceDTO(Resource resource) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.description = resource.getDescription();
        this.plcType = resource.getPlcType();
        this.ip = resource.getIp();
        this.picture = resource.getPicture();
        this.parallelProcessing = resource.getParallelProcessing();
        this.automatic = resource.getAutomatic();
        this.webPage = resource.getWebPage();
        this.openWithDefaultBrowser = resource.getOpenWithDefaultBrowser();
        this.topologyType = resource.getTopologyType();
        this.agv = resource.getAgv();
        this.resourceType = resource.getResourceType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPlcType() {
        return plcType;
    }

    public void setPlcType(Long plcType) {
        this.plcType = plcType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getParallelProcessing() {
        return parallelProcessing;
    }

    public void setParallelProcessing(Boolean parallelProcessing) {
        this.parallelProcessing = parallelProcessing;
    }

    public Boolean getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Boolean automatic) {
        this.automatic = automatic;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public Boolean getOpenWithDefaultBrowser() {
        return openWithDefaultBrowser;
    }

    public void setOpenWithDefaultBrowser(Boolean openWithDefaultBrowser) {
        this.openWithDefaultBrowser = openWithDefaultBrowser;
    }

    public Long getTopologyType() {
        return topologyType;
    }

    public void setTopologyType(Long topologyType) {
        this.topologyType = topologyType;
    }

    public Boolean getAgv() {
        return agv;
    }

    public void setAgv(Boolean agv) {
        this.agv = agv;
    }

    public Long getResourceType() {
        return resourceType;
    }

    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }
}
