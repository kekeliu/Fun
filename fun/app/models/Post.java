package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class Post extends Model{

	public String title;
	
	@ManyToOne
	public User author;
	
	@Lob
	@MaxSize(200) 
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
		Comment comment = new Comment(this,author,content);
		comment.save();
		this.comments.add(comment);
		return this;
	}
}
