package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblResource")
//Recursos disponibles en la planta (PLC)
public class Resource {
    @Id
    @Column(name = "ResourceID", nullable = false)
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
    public Resource() {
    }
    public Resource(Long testID) {
        this.id=testID;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((plcType == null) ? 0 : plcType.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((parallelProcessing == null) ? 0 : parallelProcessing.hashCode());
        result = prime * result + ((automatic == null) ? 0 : automatic.hashCode());
        result = prime * result + ((webPage == null) ? 0 : webPage.hashCode());
        result = prime * result + ((openWithDefaultBrowser == null) ? 0 : openWithDefaultBrowser.hashCode());
        result = prime * result + ((topologyType == null) ? 0 : topologyType.hashCode());
        result = prime * result + ((agv == null) ? 0 : agv.hashCode());
        result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
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
        Resource other = (Resource) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (plcType == null) {
            if (other.plcType != null)
                return false;
        } else if (!plcType.equals(other.plcType))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (parallelProcessing == null) {
            if (other.parallelProcessing != null)
                return false;
        } else if (!parallelProcessing.equals(other.parallelProcessing))
            return false;
        if (automatic == null) {
            if (other.automatic != null)
                return false;
        } else if (!automatic.equals(other.automatic))
            return false;
        if (webPage == null) {
            if (other.webPage != null)
                return false;
        } else if (!webPage.equals(other.webPage))
            return false;
        if (openWithDefaultBrowser == null) {
            if (other.openWithDefaultBrowser != null)
                return false;
        } else if (!openWithDefaultBrowser.equals(other.openWithDefaultBrowser))
            return false;
        if (topologyType == null) {
            if (other.topologyType != null)
                return false;
        } else if (!topologyType.equals(other.topologyType))
            return false;
        if (agv == null) {
            if (other.agv != null)
                return false;
        } else if (!agv.equals(other.agv))
            return false;
        if (resourceType == null) {
            if (other.resourceType != null)
                return false;
        } else if (!resourceType.equals(other.resourceType))
            return false;
        return true;
    }
}
