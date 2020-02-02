package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import org.springframework.http.HttpStatus;
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
			return new ResponseEntity<>("Image with this id is not existing", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/image/tags/{id}/{tagId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTag (@PathVariable("id")long id, @PathVariable("tagId")long tagId, @RequestParam("tags")String tag) {
		if(imageRepo.findById(id).isPresent()) {
			Image image = imageRepo.findById(id).get();
			if (tagRepo.findById(tagId).isPresent()) {
				Tag existingTag = tagRepo.findById(tagId).get();
				if (image.getTags().contains(existingTag)) {
					List<Tag> tagsInImage = image.getTags();
					for (Tag tagInImage : tagsInImage) {
						if (tagInImage.getId().equals(tagId)) {
							tagInImage.setTagName(tag);
							tagRepo.save(tagInImage);
						}
					}
					image.setTags(tagsInImage);
					imageRepo.save(image);
				}
				return ResponseEntity.ok().body("Updated!");
			}
			else
				return new ResponseEntity<>("Tag with this id is not existing!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Image with this id is not existing", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/image//tags/{id}/{tagId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTag (@PathVariable("id")long id, @PathVariable("tagId")long tagId) {
		if(imageRepo.findById(id).isPresent()) {
			if(tagRepo.findById(tagId).isPresent()) {
				Tag existingTag = tagRepo.findById(tagId).get();
				tagRepo.delete(existingTag);
				return ResponseEntity.ok().body("Deleted!");
			}
			else
				return new ResponseEntity<>("Tag with this id is not existing!", HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>("Image with this id is not existing", HttpStatus.NOT_FOUND);

	}
}
