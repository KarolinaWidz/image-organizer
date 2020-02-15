package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.exception.ImageNotFoundException;
import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.util.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ImageController {

	private final ImageRepo imageRepo;

	private final TagRepo tagRepo;

	public ImageController(ImageRepo imageRepo, TagRepo lebelRepo) {
		this.imageRepo = imageRepo;
		this.tagRepo = lebelRepo;
	}

	@Autowired
	private ImageUploader memeUploader;

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public ResponseEntity<?> getAllImages() {
		return ResponseEntity.ok().body(imageRepo.findAll());
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public Image getMeme(@PathVariable("id") long id) {
		return imageRepo.findById(id).orElseThrow(()->new ImageNotFoundException(id));
	}


	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity <?> addImage(@RequestParam("path")String path) {
		String result = memeUploader.uploadFile(path);
		Image image = new Image(result,null);
		return ResponseEntity.ok().body(imageRepo.save(image));
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
		if(imageRepo.findById(id).isPresent()){
			imageRepo.deleteById(id);
			return ResponseEntity.ok().body("Deleted!");
		}
		else
			return new ResponseEntity<>("Image with this id is not existing", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> addTagsToImage(@PathVariable("id") long id, @RequestParam("tags")List <String> tags) {
		if(tags.isEmpty())return new ResponseEntity<>("No tags", HttpStatus.NOT_FOUND);
		imageRepo.findById(id).map(element -> {
			List <Tag> tmp = new ArrayList<>();
			for(String tag:tags){
				if(tagRepo.findByTagName(tag).isEmpty())
					tmp.add(new Tag(tag,element));
			}
			element.getTags().addAll(tmp);
			return imageRepo.save(element);
		}).orElseThrow(()->new ImageNotFoundException(id));
		return ResponseEntity.ok().body("Updated!");
	}}