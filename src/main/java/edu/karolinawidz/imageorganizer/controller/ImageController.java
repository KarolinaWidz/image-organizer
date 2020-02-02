package edu.karolinawidz.imageorganizer.controller;

import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import edu.karolinawidz.imageorganizer.ImageUploader;
import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ImageController {

	private final ImageRepo imageRepo;

	private final TagRepo tagRepo;

	public ImageController(ImageRepo imageRepo, TagRepo tagRepo) {
		this.imageRepo = imageRepo;
		this.tagRepo = tagRepo;
	}

	@Autowired
	private ImageUploader imageUploader;

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public ResponseEntity <?> getAllImage() {
		return ResponseEntity.ok().body(imageRepo.findAll());
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getImage(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(imageRepo.findById(id));
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity <?> addImage(@RequestParam("path")String path) {
		String result = imageUploader.uploadFile(path);
		Image image = new Image(result,null);
		return ResponseEntity.ok().body(imageRepo.save(image));
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
		if(imageRepo.findById(id).isPresent()){
			Image image = imageRepo.findById(id).get();
			imageRepo.delete(image);
			return ResponseEntity.ok().body("Deleted!");
		}
		else
			return ResponseEntity.badRequest().body("Image with this id is not existing");
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateImage(@PathVariable("id") long id, @RequestParam("tags")List <String> tags) {
		if(imageRepo.findById(id).isPresent()){
			Image image = imageRepo.findById(id).get();
			List<Tag> tagList = new ArrayList<>();
			for (String tag:tags) {
				Tag tmp = new Tag(tag,image);
				List <Tag> existingTag = tagRepo.findByTagName(tag);
				if(!existingTag.isEmpty())
					for(Tag existing : existingTag) {
						if (!existing.getTagName().equals(tmp.getTagName())){
							tagRepo.save(tmp);
							tagList.add(tmp);
						} else
							break;
					}
				else{
					tagRepo.save(tmp);
					tagList.add(tmp);
				}
			}
			image.setTags(tagList);
			imageRepo.save(image);
			return ResponseEntity.ok().body("Updated!");
		}
		return ResponseEntity.badRequest().body("Image with this id is not existing");

	}

}











