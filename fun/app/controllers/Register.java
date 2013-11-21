package controllers;

import models.User;
import play.data.validation.Required;
import play.mvc.Controller;

public class Register extends Controller {

	public static void register(@Required String username){
		render(username);
	}
	
	public static void registerUser(String username, String userpwd, String usercontent){
		
		validation.required(username);
		validation.minSize(username, 4);
		validation.required(userpwd);
		validation.minSize(userpwd, 6);
		validation.required(usercontent);
		
		if(validation.hasErrors()){
			render("@register");
		}
		
		User user = new User(username, userpwd, usercontent);
		user.save();
		Blog.type = 0;
		Blog.blog(user.id);
	}
}
