package edu.karolinawidz.imageorganizer.repo;

import edu.karolinawidz.imageorganizer.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag,Long> {

	List <Tag> findByTagName(String tag);
}

