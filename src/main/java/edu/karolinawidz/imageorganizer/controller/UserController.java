package edu.karolinawidz.imageorganizer.controller;


import edu.karolinawidz.imageorganizer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;


@RestController
public class UserController {

	private final UserRepo userRepo;

	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

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

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public  String login(){
		return "Done";
	}


	@RequestMapping(value = "/user/tags/{tagId}", method = RequestMethod.GET)
	public String getImagesByTag(@PathVariable("tagId")int tagId) {
		return "You get images";
	}


}
