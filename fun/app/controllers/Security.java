package controllers;

import models.User;

public class Security extends Secure.Security{
	
	static boolean authenticate(String username, String userpwd){
		return User.connect(username, userpwd) != null;
	}
}
