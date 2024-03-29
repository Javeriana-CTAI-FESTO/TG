package co.edu.javeriana.ctai.tgsecurity.entities.builder.admin;


import co.edu.javeriana.ctai.tgsecurity.entities.admin.ImageData;
import co.edu.javeriana.ctai.tgsecurity.entities.builder.IBuilder;

public class ImageDataBuilder implements IBuilder<ImageData> {

    private final ImageData imageData;

    public ImageDataBuilder() {
        imageData = new ImageData();
    }


    public ImageDataBuilder withId(Long id) {
        imageData.setId(id);
        return this;
    }

    public ImageDataBuilder withName(String name) {
        imageData.setName(name);
        return this;
    }

    public ImageDataBuilder withType(String type) {
        imageData.setType(type);
        return this;
    }

    public ImageDataBuilder withImageData(byte[] imageData) {
        this.imageData.setImageData(imageData);
        return this;
    }

    @Override
    public ImageData build() {
        return imageData;
    }
}
