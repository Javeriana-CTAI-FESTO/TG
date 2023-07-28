package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Resource;

public class ResourceDTO {
    private Long id;
    private String name;
    private String description;
    private Long plcType;
    private String ip;
    private String picture;
    private Boolean parallelProcessing;
    private Boolean automatic;
    private String webPage;
    private Boolean openWithDefaultBrowser;
    private Long topologyType;
    private Boolean agv;
    private Long resourceType;

    public ResourceDTO() {
    }

    public ResourceDTO(Resource resource){
        this.id = resource.getId();
        this.name = resource.getName();
        this.description = resource.getDescription();
        this.plcType = resource.getPlcType();
        this.ip = resource.getIp();
        this.picture = resource.getPicture();
        this.parallelProcessing = resource.getParallelProcessing();
        this.automatic = resource.getAutomatic();
        this.webPage=resource.getWebPage();
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
