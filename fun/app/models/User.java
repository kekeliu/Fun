package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	@Required @MinSize(4) public String username;
	@Required @MinSize(6) public String userpwd;
	public String usercontent;
	
	public Date bornDay;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	public List<Post> posts;
	
	
	public User(String username, String userpwd, String usercontent){
		this.username = username;
		this.userpwd = userpwd;
		this.usercontent = usercontent;
		this.bornDay = new Date();
		this.posts = new ArrayList<Post>();
	}
	
	public User addPost(String title, String content){
		Post post = new Post(this, title, content);
		post.save();
		this.posts.add(post);
		return this;
	}
}
