package edu.karolinawidz.imageorganizer.repo;

import edu.karolinawidz.imageorganizer.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tag,Long> {


}
