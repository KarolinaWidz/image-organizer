package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TagController {

	private final TagRepo tagRepo;

	private final ImageRepo imageRepo;

	public TagController(TagRepo tagRepo, ImageRepo imageRepo) {
		this.tagRepo = tagRepo;
		this.imageRepo = imageRepo;
	}

	@RequestMapping(value = "/image/tags/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTags(@PathVariable("id")long id) {
		if(imageRepo.findById(id).isPresent()){
			Image image = imageRepo.findById(id).get();
			List<String> result = new ArrayList<>();
			for (Tag tag:image.getTags()) {
				result.add(tag.getTagName());
			}
			return ResponseEntity.ok().body(result);
		}
		else
			return ResponseEntity.badRequest().body("Image with this id is not existing");
	}

	@RequestMapping(value = "/image/{id}/tags/{tagId}", method = RequestMethod.PUT)
	public String updateTag (@PathVariable("id")long id, @PathVariable("tagId")long tagId) {
		return "You updated a tag";
	}

	@RequestMapping(value = "/image/{id}/tags/{tagId}", method = RequestMethod.DELETE)
	public String deleteTag (@PathVariable("id")long id, @PathVariable("tagId")long tagId) {
		return "You deleted a tag";
	}
}
