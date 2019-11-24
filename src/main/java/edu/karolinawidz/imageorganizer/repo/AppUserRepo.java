package edu.karolinawidz.imageorganizer.repo;

import edu.karolinawidz.imageorganizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository <User,Long> {
	User findByUsername(String username);
}
