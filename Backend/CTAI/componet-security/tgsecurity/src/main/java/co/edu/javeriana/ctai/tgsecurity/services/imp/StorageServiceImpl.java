package co.edu.javeriana.ctai.tgsecurity.services.imp;

import co.edu.javeriana.ctai.tgsecurity.entities.ImageData;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IStorageRepository;
import co.edu.javeriana.ctai.tgsecurity.services.IStorageService;
import co.edu.javeriana.ctai.tgsecurity.services.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements IStorageService {

    private final IStorageRepository repository;

    public StorageServiceImpl(IStorageRepository repository) {
        this.repository = repository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String uploadImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "File is empty. Please select a valid file.";
            }

            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] imageData = ImageUtils.compressImage(file.getBytes());

            // Verificar si la imagen ya existe
            if (repository.existsByName(originalFilename)) {
                return "A file with the same name already exists. Please choose a different name.";
            }

            ImageData imageDataEntity = new ImageData();
            imageDataEntity.setName(originalFilename);
            imageDataEntity.setType(contentType);
            imageDataEntity.setImageData(imageData);

            repository.save(imageDataEntity);

            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            return "Error uploading the file: " + e.getMessage();
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        } else {
            return new byte[0]; // O puedes lanzar una excepción o manejarlo de otra manera
        }
    }
}
