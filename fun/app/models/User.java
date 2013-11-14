package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String username;
	public String userpwd;
	public String usercontent;
	
	public User(String username, String userpwd, String usercontent){
		this.username = username;
		this.userpwd = userpwd;
		this.usercontent = usercontent;
	}
}
