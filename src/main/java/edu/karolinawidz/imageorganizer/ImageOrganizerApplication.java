package edu.karolinawidz.imageorganizer;

import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ImageOrganizerApplication {

	/*@Transactional
	public void run()throws IOException{
		Set<Tag> tags = new HashSet<>();
		Image img1= new Image("test1",tags);
		tags.add(new Tag("dupa",img1));
		tags.add(new Tag("kupa",img1));
		img1= new Image("lalal",tags);
		img1.setTags(tags);

		Set<Tag> tags2 = new HashSet<>();
		Image img2= new Image("lalal",tags2);
		tags2.add(new Tag("dupa",img2));
		tags2.add(new Tag("kupa",img2));
		img2= new Image("lalal",tags2);
		img2.setTags(tags2);
		imageRepo.saveAll(Arrays.asList(img1,img2));
	}*/
	@Autowired
	private ImageRepo imageRepo;
	public static void main(String[] args) {
		SpringApplication.run(ImageOrganizerApplication.class, args);
	}




}
