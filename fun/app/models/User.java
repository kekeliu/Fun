package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String username;
	public String userpwd;
	public String usercontent;
	
	//@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	//public List<Post> posts;
	
	public User(String username, String userpwd, String usercontent){
		this.username = username;
		this.userpwd = userpwd;
		this.usercontent = usercontent;
	}
	
	/*public User addPost(String title, String content){
		this.posts.add(new Post(this, title, content));
		this.save();
		return this;
	}*/
}
