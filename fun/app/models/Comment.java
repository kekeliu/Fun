package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model{
	
	@ManyToOne
	public Post post;
	
	public Date postedAt;
	
	@Lob
	public String content;
	public String author;
	
	public Comment(Post post, String author, String content){
		this.author = author;
		this.content = content;
		this.post = post;
	}
}
