package co.edu.javeriana.ctai.tgsecurity.services.interfaces.admin;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IStorageService {

    String uploadImage(MultipartFile file);

    byte[] downloadImage(String fileName);

    List<String> getAllImageNames();
}
