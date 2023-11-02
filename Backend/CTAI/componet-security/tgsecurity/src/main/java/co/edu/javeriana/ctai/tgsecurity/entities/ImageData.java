package co.edu.javeriana.ctai.tgsecurity.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String sizeMB;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    // Constructor sin argumentos
    public ImageData() {
    }

    // Constructor con todos los campos
    public ImageData(String name, String type, String sizeMB, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.sizeMB = sizeMB;
        this.imageData = imageData;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSizeMB() {
        return sizeMB;
    }

    public void setSizeMB(String sizeMB) {
        this.sizeMB = sizeMB;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageData imageData1 = (ImageData) o;

        if (!Objects.equals(id, imageData1.id)) return false;
        if (!Objects.equals(name, imageData1.name)) return false;
        return Objects.equals(type, imageData1.type);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
