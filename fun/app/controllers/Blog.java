package controllers;

import java.util.List;

import models.Post;
import models.User;

import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;

public class Blog extends Controller {
	
	public static int type = 0;
	private static String  url = "";
	
	public static void blog(Long user_id){
		
		int sType = type;
		List<Post> posts = null;
		User user = User.findById(user_id);
		if(type == 0){
			url = "select p from Post p order by postedAt asc";
			posts = Post.find(url).fetch();
			//posts = Post.find("order by postedAt asc").fetch();
		}else if(type == 1){
			url = "select p from Post p where p.author.id=" + user_id + "order by postedAt asc"; 
			posts = Post.find(url).fetch();
			//posts = Post.find("byAuthor", user).fetch();
		}
		
		render(posts, user, sType);
	}
	
	public static void newPost(Long user_id){
		render(user_id);
	}
	
	public static void showMyHomePage(Long user_id){
		 type = 2;
		blog(user_id);
	}
	
	public static void deletePost(Long post_id, Long user_id){
		User user = User.findById(user_id);
		user.deletePost(post_id);
		blog(user.id);
	}
	
	public static void save(Long post_id, Long user_id,String title, String content){
		User user = User.findById(user_id);
		validation.required(user);
		if(validation.hasErrors()){
			user.refresh();
		}else{
			user.addPost(title, content);
			type = 0;
		}
		blog(user.id);
	}
	
	public static void saveComment(Long post_id,Long user_id,String comment){
		Post post = Post.findById(post_id);
		User user = User.findById(user_id);
		post.addComments(user.username, comment);
		type = 0;
		blog(user_id);
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
		ValuePaginator paginator = new ValuePaginator(post.comments);
		paginator.setPageSize(3);
		if(page == null || page < 0)
			page = 1;
		paginator.setPageNumber(page);
		render(post,post_id,paginator);
	}
}
