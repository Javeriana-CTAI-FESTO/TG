package co.edu.javeriana.ctai.tgsecurity.services;

import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IStorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IStorageService {

    public String uploadImage(MultipartFile file);
    public byte[] downloadImage(String fileName);

}
