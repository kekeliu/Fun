package controllers;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import models.Comment;
import models.Post;
import models.User;
import play.cache.Cache;
import play.mvc.Controller;

public class Blog extends Controller {
	
	public static int type = 0;
	
	public static void blog(Long user_id){
		List<Post> posts = null;
		User user = User.findById(user_id);
		if(type == 0){
			posts = Post.find("order by postedAt asc ").fetch();
		}else if(type == 1){
			posts = Post.find("byUser", user).fetch();
		}
		
		render(posts, user);
	}
	
	public static void newPost(Long user_id){
		render(user_id);
	}
	
	public static void save(Long post_id, Long user_id,String title, String content){
		User user = User.findById(user_id);
		user.addPost(title, content);
		type = 0;
		blog(user.id);
	}
	
	public static void saveComment(Long post_id,Long user_id,String comment){
		Post post = Post.findById(post_id);
		User user = User.findById(user_id);
		post.addComments(user.username, comment);
		type = 0;
		blog(user.id);
	}
	public static void publishComment(Long id){
		Post newPost;
		if(id != null){
			newPost = Post.findById(id);
			render(newPost);
		}
	}
	
	public static void showAllBlogs(Long user_id){
		 type = 0;
		 blog(user_id);
	}
	
	public static void showMyBlogs(Long user_id){
		 type = 1;
		 blog(user_id);
	}
	
	public static void showAllComments(Long post_id,Integer page){
		Post post = Post.findById(post_id);
		if(page == null)
			page = 1;
		render(post,page,post_id);
	}
	
}
