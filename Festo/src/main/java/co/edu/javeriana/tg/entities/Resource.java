package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblResource")
public class Resource {
    @Id
    @Column(name = "ResourceID")
    private Long id;
    @Column(name = "ResourceName")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "PlcType")
    private Long plcType;
    @Column(name = "IP")
    private String ip;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "ParallelProcessing")
    private Boolean parallelProcessing;
    @Column(name = "Automatic")
    private Boolean automatic;
    @Column(name = "WebPage")
    private String webPage;
    @Column(name = "DefaultBrowser")
    private Boolean openWithDefaultBrowser;
    @Column(name = "TopologyType")
    private Long topologyType;
    @Column(name = "AGV")
    private Boolean agv;
    @Column(name = "ResourceType")
    private Long resourceType;
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
    public void setDescription(String descripction) {
        this.description = descripction;
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
