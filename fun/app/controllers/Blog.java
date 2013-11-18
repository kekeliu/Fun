package controllers;

import java.security.Security;
import java.util.List;

import models.Comment;
import models.Post;
import models.User;
import play.cache.Cache;
import play.mvc.Controller;

public class Blog extends Controller {
	
	public static User author;
	public static Post cPost;
	
	public static void blog(User user){
		author = user;
	     List<Post> posts = Post.find("order by postedAt desc").fetch();
	     //= Post.find("byAuthor", user).fetch();
		render(user, posts);
	}
	
	public static void newPost(){
		render();
	}
	
	public static void save(Long id, String title, String content){
		Post post;
		if(id == null){
			 post = new Post(author, title, content);
		}else{
			post = Post.findById(id);
			post.title = title;
			post.content = content;
		}
        post.save();
       blog(author);
	}
	
	public static void saveComment(String comment){
		//System.out.println("author = "+author.username);
		Comment ncomment = new Comment(cPost,author.username,comment);
		cPost.comments.add(ncomment);
		ncomment.save();
		//cPost.addComments(author.username, comment);
		blog(author);
	}
	public static void publishComment(Long id){
		Post newPost;
		if(id != null){
			newPost = Post.findById(id);
			cPost = newPost;
			render(newPost);
		}
	}
}
