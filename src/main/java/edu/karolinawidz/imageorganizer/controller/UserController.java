package edu.karolinawidz.imageorganizer.controller;


import edu.karolinawidz.imageorganizer.UserDetailsServiceImpl;
import edu.karolinawidz.imageorganizer.model.User;
import edu.karolinawidz.imageorganizer.model.UserResponse;
import edu.karolinawidz.imageorganizer.repo.UserRepo;
import edu.karolinawidz.imageorganizer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;


@RestController
public class UserController {

	private final UserRepo userRepo;

	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
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


	@RequestMapping(value = "/user/tags/{tagId}", method = RequestMethod.GET)
	public String getImagesByTag(@PathVariable("tagId")int tagId) {
		return "You get images";
	}


}
