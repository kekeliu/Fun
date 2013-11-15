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
	
	public static void save(Long id, String title, String content){
		Post post;
		String username = (String)session.get("username");
		System.out.println("username="+username);/*
		User author = User.find("byUsername", userName).first();
		if(id == null){
			 post = new Post(author, title, content);
		}else{
			post = Post.findById(id);
			post.title = title;
			post.content = content;
		}
		
		validation.valid(post);
        if(validation.hasErrors()) {
            render("@form", post);
        }
        post.save();
       log(userName);*/
	}
}
