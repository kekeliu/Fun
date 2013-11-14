package controllers;

import play.*;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void checkUser(@Required String username, @Required String userpwd){
    	User user = User.find("byUsername", username).first();
    	 validation.required(user);
    	if(user != null){
    		Loging.log(user);
    		return;
    	} else{
    		Register.register(username, userpwd);
    	}
    }

}