package edu.karolinawidz.imageorganizer.controller;

import edu.karolinawidz.imageorganizer.model.Image;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
public class ImageController {

	private final ImageRepo imageRepo;

	private final TagRepo tagRepo;

	public ImageController(ImageRepo imageRepo, TagRepo tagRepo) {
		this.imageRepo = imageRepo;
		this.tagRepo = tagRepo;
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String getAllImage() {
		return "You get all Images";
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String getImage(@PathVariable("id") long id) {
		return "You get Image";
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public String addImage() {
		return "You added Image";
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
	public String deleteImage(@PathVariable("id") long id) {
		return "You deleted Image";
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.PUT)
	public String updateImage(@PathVariable("id") long id) {
		return "You updated Image";
	}

}











