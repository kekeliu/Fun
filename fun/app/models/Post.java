package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Post extends Model{

	public String title;
	public User author;
	
	@Lob
	public String content;
	public Date postedAt;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Comment> comments;
	
	public Post(User author, String title, String content){
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public Post addComments(String author, String content){
		this.comments.add(new Comment(this,author,content));
		this.save();
		return this;
	}
}
