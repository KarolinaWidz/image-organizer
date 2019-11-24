package edu.karolinawidz.imageorganizer.repo;

import edu.karolinawidz.imageorganizer.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository <AppUser,Long> {
	AppUser findByUsername(String username);
}
