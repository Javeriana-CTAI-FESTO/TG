package co.edu.javeriana.ctai.tgsecurity.services.impl.admin;

import co.edu.javeriana.ctai.tgsecurity.entities.admin.ImageData;
import co.edu.javeriana.ctai.tgsecurity.repository.impl.StorageJPARepositoryImpl;
import co.edu.javeriana.ctai.tgsecurity.services.interfaces.admin.IStorageService;
import co.edu.javeriana.ctai.tgsecurity.services.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class StorageServiceImpl implements IStorageService {

    private static final Logger LOGGER = Logger.getLogger(StorageServiceImpl.class.getName());
    private static final String FILE_EMPTY_MESSAGE = "File is empty. Please select a valid file.";
    private static final String UPLOAD_SUCCESS_MESSAGE = "File uploaded successfully: ";
    private static final String ERROR_UPLOAD_MESSAGE = "Error uploading the file: ";
    private final StorageJPARepositoryImpl repository;

    public StorageServiceImpl(StorageJPARepositoryImpl repository) {
        this.repository = repository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String uploadImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return FILE_EMPTY_MESSAGE;
            }

            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] imageData = ImageUtils.compressImage(file.getBytes());

            // Verificar si la imagen ya existe
            Optional<ImageData> existingImageData = Optional.ofNullable(repository.findByName(originalFilename));
            if (existingImageData.isPresent()) {
                // Si la imagen ya existe, simplemente sobrescribe sus datos
                ImageData imageDataEntity = existingImageData.get();
                imageDataEntity.setType(contentType);
                imageDataEntity.setImageData(imageData);
                imageDataEntity.setSizeMB(String.valueOf(file.getSize() / 1024));
                repository.save(imageDataEntity);
                LOGGER.log(Level.INFO, "Imagen sobre-escrita: " + originalFilename);

            } else {
                // Si la imagen no existe, crea una nueva entidad
                ImageData imageDataEntity = new ImageData();
                imageDataEntity.setName(originalFilename);
                imageDataEntity.setType(contentType);
                imageDataEntity.setImageData(imageData);
                imageDataEntity.setSizeMB(String.valueOf(file.getSize() / 1024));
                repository.save(imageDataEntity);
                LOGGER.log(Level.INFO, "Imagen guardada: " + originalFilename);

            }

            return UPLOAD_SUCCESS_MESSAGE + originalFilename;

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se puede cargar la imagen: " + e.getMessage(), e);
            return ERROR_UPLOAD_MESSAGE + e.getMessage();
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public byte[] downloadImage(String fileName) {

        Optional<ImageData> dbImageData = Optional.ofNullable(repository.findByName(fileName));

        if (dbImageData.isPresent()) {
            LOGGER.log(Level.INFO, "Imagen descargada: " + fileName);
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        } else {
            LOGGER.log(Level.WARNING, "Imagen no encontrada: " + fileName);
            return new byte[0];
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<String> getAllImageNames() {

        return repository.findAllNames();
    }
}
