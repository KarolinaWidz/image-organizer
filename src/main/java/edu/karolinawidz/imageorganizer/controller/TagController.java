package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.exception.ImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

	private final TagRepo tagRepo;

	private final ImageRepo imageRepo;

	public TagController(TagRepo tagRepo, ImageRepo imageRepo) {
		this.tagRepo = tagRepo;
		this.imageRepo = imageRepo;
	}

	@RequestMapping(value = "/image/tags/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTags(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(imageRepo.findById(id).map(element -> element.getTags())
				.orElseThrow(() -> new ImageNotFoundException(id)
				));
	}

	@RequestMapping(value = "/image//tags/{id}/{tagId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTag(@PathVariable("id") long id, @PathVariable("tagId") long tagId) {
		if (imageRepo.findById(id).isPresent()) {
			if (tagRepo.findById(tagId).isPresent()) {
				Tag existingTag = tagRepo.findById(tagId).get();
				tagRepo.delete(existingTag);
				return ResponseEntity.ok().body("Deleted!");
			} else
				return new ResponseEntity<>("Tag with this id is not existing!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Image with this id is not existing", HttpStatus.NOT_FOUND);

	}

}

