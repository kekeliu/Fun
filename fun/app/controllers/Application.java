package controllers;

import play.*;
import play.cache.Cache;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void checkUser(@Required String username){
    	User user = User.find("byUsername", username).first();
    	 validation.required(user);
    	if(user != null){
    		session.put("username", username);
    		Blog.type = 0;
    		Blog.blog(user.id);
    		return;
    	} else{
    		Register.register(username);
    	}
    }

}