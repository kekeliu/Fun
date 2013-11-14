package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;

public class Loging extends Controller {
	
	public static void log(User user){
		render(user);
	}
}
