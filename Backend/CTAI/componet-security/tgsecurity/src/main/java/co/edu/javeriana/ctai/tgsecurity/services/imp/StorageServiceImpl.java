package co.edu.javeriana.ctai.tgsecurity.services.imp;

import co.edu.javeriana.ctai.tgsecurity.entities.ImageData;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IStorageRepository;
import co.edu.javeriana.ctai.tgsecurity.services.IStorageService;
import co.edu.javeriana.ctai.tgsecurity.services.utils.ImageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final IStorageRepository repository;

    public StorageServiceImpl(IStorageRepository repository) {
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
            Optional<ImageData> existingImageData = repository.findByName(originalFilename);
            if (existingImageData.isPresent()) {
                // Si la imagen ya existe, simplemente sobrescribe sus datos
                ImageData imageDataEntity = existingImageData.get();
                imageDataEntity.setType(contentType);
                imageDataEntity.setImageData(imageData);
                repository.save(imageDataEntity);
                LOGGER.log(Level.INFO, "Imagen guardada: " + originalFilename.toString());
            } else {
                // Si la imagen no existe, crea una nueva entidad
                ImageData imageDataEntity = new ImageData();
                imageDataEntity.setName(originalFilename);
                imageDataEntity.setType(contentType);
                imageDataEntity.setImageData(imageData);
                repository.save(imageDataEntity);
                LOGGER.log(Level.INFO, "Imagen sobre-escrita: " + originalFilename);
            }

            return UPLOAD_SUCCESS_MESSAGE + originalFilename;

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se puede cargar la imagen: " + e.getMessage(), e);
            return ERROR_UPLOAD_MESSAGE + e.getMessage();
        }
    }


    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public byte[] downloadImage(String fileName) {

        Optional<ImageData> dbImageData = repository.findByName(fileName.toString());

        if (dbImageData.isPresent()) {
            LOGGER.log(Level.INFO, "Imagen descargada: " + fileName.toString());
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        } else {
            LOGGER.log(Level.WARNING, "Imagen no encontrada: " + fileName.toString());
            return new byte[0]; // O puedes lanzar una excepción o manejarlo de otra manera
        }
    }

    @Override
    public List<ImageData> getAllImages() {
        List<ImageData> dbImageData = repository.findAll();
        return dbImageData;
    }

}