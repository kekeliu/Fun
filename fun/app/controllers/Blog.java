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
	
	public static int type = 0;
	
	public static void blog(User user){
		author = user;
		
		List<Post> posts = null;
		if(type == 0){
			posts = Post.find("order by postedAt asc ").fetch();
		}else if(type== 1){
			//String sql = "select p from Post p where p.author.username="+user.username+" order by postedAt asc";
			//posts = Post.find(sql).fetch();
			posts = Post.find("byAuthor", author).fetch();
		}
		render(posts, user);
	}
	
	public static void newPost(){
		render();
	}
	
	public static void save(Long id, String title, String content){
		Post post;
		if(id == null){
			 //post = new Post(author, title, content);
			 post = new Post(author, title, content);
		}else{
			post = Post.findById(id);
			post.title = title;
			post.content = content;
		}
		
		validation.valid(post);
        post.save();
        type = 0;
       blog(author);
	}
	
	public static void saveComment(String comment){
		//System.out.println("author = "+author.username);
		Comment ncomment = new Comment(cPost,author.username,comment);
		cPost.comments.add(ncomment);
		ncomment.save();
		//cPost.addComments(author.username, comment);
		type = 0;
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
	
	public static void showAllBlogs(){
		 type = 0;
		 blog(author);
	}
	
	public static void showMyBlogs(){
		 type = 1;
		 blog(author);
	}
	
	public static void showAllComments(Long id,Integer page){
		Post post = Post.findById(id);
		if(page == null)
			page = 1;
		render(post,page,id);
	}
	
}
