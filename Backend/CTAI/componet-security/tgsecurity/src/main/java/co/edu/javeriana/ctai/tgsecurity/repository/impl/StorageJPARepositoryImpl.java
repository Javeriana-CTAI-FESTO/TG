package co.edu.javeriana.ctai.tgsecurity.repository.impl;

import co.edu.javeriana.ctai.tgsecurity.entities.admin.ImageData;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.admin.IStorageRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class StorageJPARepositoryImpl implements IStorageRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(ImageData imageData) {
        em.persist(imageData);
    }

    @Override
    public ImageData findByName(String fileName) {
        TypedQuery<ImageData> query = em.createQuery("SELECT i FROM ImageData i WHERE i.name = :fileName", ImageData.class);
        query.setParameter("fileName", fileName);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Get all images
    @Override
    public List findAll() {
        return em.createNativeQuery("SELECT * FROM image_data", ImageData.class)
                .getResultList();
    }

    // Get all image names
    public List findAllNames() {
        return em.createNativeQuery("SELECT name FROM image_data")
                .getResultList();
    }

    // Get all image names and data
    @Override
    public List findAllNamesAndData() {
        return em.createNativeQuery("SELECT name, imagedata FROM image_data")
                .getResultList();
    }

}
