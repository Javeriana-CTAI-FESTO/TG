package co.edu.javeriana.ctai.tgsecurity.controller.web.admin;

import co.edu.javeriana.ctai.tgsecurity.entities.ImageData;
import co.edu.javeriana.ctai.tgsecurity.services.IStorageService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin/storage")
public class StorageController {

    private final IStorageService service;


    public StorageController(@Qualifier("storageServiceImpl") IStorageService service) {
        this.service = service;
    }


    @PostMapping("image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("image/get/fileName={fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    // Get all images
    @GetMapping("image/get/all")
    public ResponseEntity<List<String>> getAllImages() {
        List<ImageData> imageDataList = service.getAllImages();
        if (!imageDataList.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(imageDataList.stream()
                    .map(ImageData::getName)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

