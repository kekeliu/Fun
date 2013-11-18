package controllers;

import models.User;
import play.data.validation.Required;
import play.mvc.Controller;

public class Register extends Controller {

	public static void register(@Required String username, @Required String userpwd){
		render(username, userpwd);
	}
	
	public static void registerUser(String username, String userpwd, String usercontent){
		User user = new User(username, userpwd, usercontent).save();
		Blog.type = 0;
		Blog.blog(user);
	}
}
