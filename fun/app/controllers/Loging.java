package controllers;

import java.security.Security;
import java.util.List;

import models.Post;
import models.User;
import play.cache.Cache;
import play.mvc.Controller;

public class Loging extends Controller {
	
	public static String userName="";
	
	public static void log(String username){
		userName = username;
		User author = User.find("byUsername", username).first();
	     List<Post> posts = Post.find("byAuthor", author).fetch();
		render(author, posts);
	}
	
	public static void newPost(){
		render();
	}
	
}
