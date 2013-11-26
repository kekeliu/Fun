package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	@Required @MinSize(4) public String username;
	@Required @MinSize(6) public String userpwd;
	
	@Required public String usercontent;
	
	public Date bornday;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	public List<Post> posts;
	
	
	public User(String username, String userpwd, String usercontent, Date bornday){
		this.username = username;
		this.userpwd = userpwd;
		this.usercontent = usercontent;
		this.bornday = bornday;
		this.posts = new ArrayList<Post>();
	}
	
	public User addPost(String title, String content){
		Post post = new Post(this, title, content);
		post.save();
		this.posts.add(post);
		return this;
	}
	
	public User deletePost(Long post_id){
		Post post = Post.findById(post_id);
		post.delete();
		this.posts.remove(post);
		return this;
	}
	
	public static User connect(String username, String userpwd){
		return find("byUsernameAndUserpwd", username, userpwd).first();
	}
}
