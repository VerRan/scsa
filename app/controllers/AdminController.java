package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class AdminController extends Controller {
	
	public static void index(){
		 String user = Security.connected();
		 if(user==null){
			 renderTemplate("Secure/login.html");//定制管理应用，后台管理应用默认首页为安全模块的登陆页面
			}else{
			 renderTemplate("CRUD/index.html",user);	
			}
		 }
		
}
