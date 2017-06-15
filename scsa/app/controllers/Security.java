package controllers;

import models.SysUser;
import play.Logger;

public class Security extends Secure.Security {

	static boolean authenticate(String email, String password) {
		if(email.equals("admin")&&password.equals("1234")){
			Logger.info("this is super administrator", email);
			return true;
		}else{
			SysUser user = SysUser.find("email", email).first();
			return user != null && user.password.equals(password);
		}

	}

	 static boolean check(String profile) {
		 SysUser user = SysUser.find("email", connected()).first();
	        if (profile.equals("administrator")&&user.role.equals(profile)) {
	             return true;//当前用户是管理员
	        }
	        else {
	            return false;
	        }
	    }    
	 
	static void onAuthenticated() {
		renderTemplate("CRUD/index.html");//定制管理应用，后台管理认证成功后使用CRUD的首页
	}

//	static void onDisconnected() {
//		Application.index();
//	}

}