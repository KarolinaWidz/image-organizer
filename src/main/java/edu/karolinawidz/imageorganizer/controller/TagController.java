package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.model.Tag;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Repository
public class TagController {

	private final TagRepo tagRepo;

	private final ImageRepo imageRepo;

	public TagController(TagRepo tagRepo, ImageRepo imageRepo) {
		this.tagRepo = tagRepo;
		this.imageRepo = imageRepo;
	}


	//http://localhost:8080/image?id=2/tags
	@RequestMapping(value = "/image/{id}/tags", method = RequestMethod.GET)
	public String getAllTags(@PathVariable("id")long id) {
		return "You get all tags";
	}

	//http://localhost:8080/image?id=2/tags
	@RequestMapping(value = "/image/{id}/tags", method = RequestMethod.POST)
	public String createTag (@PathVariable("id")long id) {
		return "You created a tag";
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
