package edu.karolinawidz.imageorganizer.controller;


import edu.karolinawidz.imageorganizer.UserDetailsServiceImpl;
import edu.karolinawidz.imageorganizer.model.*;
import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.repo.TagRepo;
import edu.karolinawidz.imageorganizer.repo.UserRepo;
import edu.karolinawidz.imageorganizer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

	private final UserRepo userRepo;

	private final TagRepo tagRepo;

	private final ImageRepo imageRepo;

	public UserController(UserRepo userRepo, TagRepo tagRepo, ImageRepo imageRepo) {
		this.userRepo = userRepo;
		this.tagRepo = tagRepo;
		this.imageRepo = imageRepo;
	}


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;


	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@RequestMapping(value = "/user", method =  RequestMethod.POST)
	public String addUser(){
		return "You added user";
	}

	@RequestMapping(value ="/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id")int id){
		return "You get the user";
	}

	@RequestMapping(value="/user/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody String deleteUser(@PathVariable("id")int id){
		return "You delete the user";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToke(@RequestBody User authenticationRequest )throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e){
			throw new Exception("Incorrect username or password",e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new UserResponse(jwt));
	}


	@RequestMapping(value = "/user/tags/{tagName}", method = RequestMethod.GET)
	public ResponseEntity<?> getImagesByTag(@PathVariable("tagName")String tagName) {
		if(!imageRepo.findAll().isEmpty()){
			List<ImageResult> result = new ArrayList<>();
			for (Image image: imageRepo.findAll()) {
				List <Tag> existingTag = image.getTags();
				if(!existingTag.isEmpty()) {
					for (Tag existing : existingTag) {
						if (existing.getTagName().equals(tagName)) {
							List<String> tmp = new ArrayList<>();
							for (Tag tag : image.getTags()) {
								tmp.add(tag.getTagName());
							}
							result.add(new ImageResult(image.getId(), image.getImagePath(), tmp));
						}
					}
				}
				else{
					break;
				}
			}

			return ResponseEntity.ok().body(result);
		}
		return ResponseEntity.badRequest().body("You don't have any images");
	}


}
