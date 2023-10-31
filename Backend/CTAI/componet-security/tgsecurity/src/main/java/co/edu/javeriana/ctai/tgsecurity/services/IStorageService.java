package co.edu.javeriana.ctai.tgsecurity.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IStorageService {

    public String uploadImage(MultipartFile file);

    public byte[] downloadImage(String fileName);

}
