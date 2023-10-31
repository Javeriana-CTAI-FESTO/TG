package co.edu.javeriana.ctai.tgsecurity.repository.interfaces;

import co.edu.javeriana.ctai.tgsecurity.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStorageRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);

    boolean existsByName(String originalFilename);
}