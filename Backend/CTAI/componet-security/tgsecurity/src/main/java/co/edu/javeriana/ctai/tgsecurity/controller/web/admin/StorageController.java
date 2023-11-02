package co.edu.javeriana.ctai.tgsecurity.controller.web.admin;

import co.edu.javeriana.ctai.tgsecurity.services.IStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin/storage")
public class StorageController {

    private final IStorageService service;


    public StorageController(@Qualifier("storageServiceImpl") IStorageService service) {
        this.service = service;
    }


    @PostMapping("image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        if (file == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty. Please select a valid file.");
        }

        String uploadImage = service.uploadImage(file);

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(uploadImage);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("image/get/fileName={fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        if (fileName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        byte[] imageData = service.downloadImage(fileName);

        if (imageData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    // Get all images
    @GetMapping("/image/get/all")
    public ResponseEntity<?> getAllImageNames() {

        List<String> imageNames = service.getAllImageNames();

        if (imageNames == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(imageNames);
        }
    }


}

