package co.edu.javeriana.ctai.tgsecurity.services;

import co.edu.javeriana.ctai.tgsecurity.entities.ImageData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IStorageService {

    public String uploadImage(MultipartFile file);

    public byte[] downloadImage(String fileName);

    List<ImageData> getAllImages();
}
