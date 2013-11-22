package controllers;

import java.util.Date;
import java.util.Random;

import models.User;
import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Images;
import play.mvc.Controller;

public class Register extends Controller {

	public static void register(@Required String username){
		Random random = new Random();
		String randomID = String.valueOf(random.nextInt());
		System.out.println("randomID ==" + randomID);
		render(username, randomID);
	}
	
	public static void captcha(String randomID){
		System.out.println("id == " + randomID);
		Images.Captcha captcha = Images.captcha();
		String code = captcha.getText("#E4EAFD");
		Cache.set(randomID, code, "10mn");
		renderBinary(captcha);
	}
	
	public static void registerUser(String username, String userpwd, String usercontent, Date bornday, String code, String randomID){
		
		validation.required(username).message("userName is required");
		validation.minSize(username, 4).message("At least 4");
		validation.required(userpwd).message("userPwd is required");
		validation.minSize(userpwd, 6).message("At least 6");
		validation.required(usercontent).message("userType is required");
		validation.required(bornday).message("bornDay is required");
		 
		validation.equals(code, Cache.get(randomID)).message("Invalid code");
		
		System.out.println("code=="+code);
		System.out.println("cache randomID=="+Cache.get(randomID));
		
		if(validation.hasErrors()){
			render("@register");
		}
		
		User user = new User(username, userpwd, usercontent, bornday);
		user.save();
		Cache.delete(randomID);
		Blog.type = 0;
		Blog.blog(user.id);
	}
}
