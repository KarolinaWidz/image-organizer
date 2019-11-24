package edu.karolinawidz.imageorganizer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

	@RequestMapping(value = "/adduser", method =  RequestMethod.POST)
	public String addUser(){
		return "You added user";
	}

	@RequestMapping(value ="getuser/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id")int id){
		return "You get the user";
	}

	@RequestMapping(value="/deleteuser/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody String deleteUser(@PathVariable("id")int id){
		return "You delete the user";
	}

	@RequestMapping(value = "/add/{path}/{name}", method = RequestMethod.POST)
	public String addImage(@PathVariable("path")String path, @PathVariable("name")String name){
		return "You added Image";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public  String login(){
		return "Done";
	}







}
