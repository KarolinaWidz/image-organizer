package edu.karolinawidz.imageorganizer.controller;


import edu.karolinawidz.imageorganizer.repo.TagRepo;
import edu.karolinawidz.imageorganizer.repo.UserRepo;
import edu.karolinawidz.imageorganizer.security.UserDetailsServiceImpl;
import edu.karolinawidz.imageorganizer.model.User;
import edu.karolinawidz.imageorganizer.model.UserResponse;

import edu.karolinawidz.imageorganizer.repo.ImageRepo;
import edu.karolinawidz.imageorganizer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<?> addUser(@RequestHeader("login") String login, @RequestHeader("password") String password){
		if(!login.isEmpty() || !password.isEmpty()){
			List <User> existingUsers = userRepo.findAll();
			if(!existingUsers.isEmpty()){
				for (User user : existingUsers) {
					if (!user.getUsername().equals(login)) {
						User tmp = new User(login, password);
						userRepo.save(tmp);
						return ResponseEntity.ok("You add user!");
					} else
						return new ResponseEntity<>("User with this username is already exist", HttpStatus.CONFLICT);
				}
			}
			else {
				User tmp = new User(login, password);
				userRepo.save(tmp);
				return ResponseEntity.ok("You add user!");
			}

		}
		return new ResponseEntity<>("Missing Data", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToke(@RequestHeader("login") String login, @RequestHeader("password") String password){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,password));
		}
		catch (BadCredentialsException e){
			return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(login);
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new UserResponse(jwt));
	}


	@RequestMapping(value = "/user/tags/{tagName}", method = RequestMethod.GET)
	public ResponseEntity<?> getImagesByTag(@PathVariable("tagName")String tagName) {
		if(!tagRepo.findByTagName(tagName).isEmpty())
			return ResponseEntity.ok().body(tagRepo.findByTagName(tagName));
		else
			return new ResponseEntity<>("You don't have any images with this tag", HttpStatus.NOT_FOUND);
	}}
