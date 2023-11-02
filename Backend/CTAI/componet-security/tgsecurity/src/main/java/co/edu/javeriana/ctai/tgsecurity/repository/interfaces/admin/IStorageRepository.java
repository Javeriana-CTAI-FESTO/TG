package co.edu.javeriana.ctai.tgsecurity.repository.interfaces.admin;

import co.edu.javeriana.ctai.tgsecurity.entities.admin.ImageData;

import java.util.List;


public interface IStorageRepository {
    ImageData findByName(String fileName);

    // Get all images
    List<ImageData> findAll();

    List findAllNamesAndData();

    void save(ImageData imageData);

}