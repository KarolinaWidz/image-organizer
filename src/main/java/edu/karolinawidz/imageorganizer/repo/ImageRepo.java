package edu.karolinawidz.imageorganizer.repo;

import edu.karolinawidz.imageorganizer.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
}



