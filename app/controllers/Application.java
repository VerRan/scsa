package controllers;

import play.cache.Cache;
import play.data.validation.*;
import play.libs.Images;
import play.mvc.*;
import java.util.*;
import models.*;
 
public class Application extends Controller {

	/** 通过拦截器实现非法访问 **/
	@Before(unless = { "index", "login", "registerPage", "register","captcha","courseList","testApi" })
	static void checkAuthentification() {
		if (Cache.get(session.getId()) == null) {
			index();
		}
	}
	
	/** 首页 */
	public static void index() {
		render();
	}

	/** 注册页面 */
	public static void registerPage() {
		render();
	}

	public static void register(@Valid SysUser sysUser) {
		
		//1. 校验是否之前注册过
		SysUser findUserResult=	SysUser.find("userName=?", sysUser.userName).first();
		if(findUserResult!=null&&!(findUserResult.userName.equals(""))){//根据用户名已经查到有同名的用户时返回已注册
			validation.addError(sysUser.userName, "该用户已注册，请更换其它用户名！");
		}
		
		if (validation.hasErrors()) {
			params.flash(); // 将HTTP参数保存在Flash作用域中
			validation.keep(); // 在下一个请求中保持错误信息的集合
			registerPage();
		}
		//2. 创建用户信息
		sysUser.save();
		
		//3. 创建学生信息
		Student student =new Student();
		student.studentCode=sysUser.email;
		student.age=sysUser.age;
		student.sex=sysUser.sex; 
		student.studentName=sysUser.userName;
		student.save();
		
		index();
	}

	/** 登陆方法 */
	public static void login( String email,  String password, String captcha) {
	 
		if(!captcha.equalsIgnoreCase(flash.get("captcha"))){
			validation.addError(captcha, "验证码不正确");
		}
			validation.required(email).message("邮箱不能为空");
			validation.required(password).message("密码不能为空");
		
		SysUser sysUser = new SysUser();
		sysUser = SysUser.find("email=? ", email).first();
		if (sysUser != null) {
			if(sysUser.password.equals(password)){
				Cache.set(session.getId(), sysUser);
			}else{
				validation.addError(password, "密码不正确");
			}			
		} else {
			validation.addError(email, "用户不存在");
		}

		/**将错误信息传递到下一个页面用于显示*/
		if (validation.hasErrors()) {
			params.flash(); 
			validation.keep(); 
			index();
		}
		
		main();
	}
	
	
	/**首页*/
	public static void main(){
		SysUser sysUser = (SysUser)Cache.get(session.getId());
		List studentCourseList = StudentCourses.find("byStudent", Student.find("studentCode=? ", sysUser.email).first()).fetch();
		renderTemplate("Application/mainPage.html", sysUser,studentCourseList);
	}

	
	/**选课页面*/
	public static void selectCourse(){
    	
		SysUser sysUser = (SysUser)Cache.get(session.getId());
		
		List citys = City.findAll();
		List schools=School.findAll();
		List majors=Major.findAll();
		List courses=Course.findAll();
		
		renderTemplate("Application/selectCourse.html", sysUser,citys,schools,majors,courses);
	}
	
	
	/**选课信息保存*/
	public static void selectCourseSave(@Valid StudentCourses studentCourses){
		checkAuthenticity();
		SysUser sysUser = (SysUser)Cache.get(session.getId());
		
		if (validation.hasErrors()) {
			params.flash(); // 将HTTP参数保存在Flash作用域中
			validation.keep(); // 在下一个请求中保持错误信息的集合
			selectCourse();
		}
		
		//1. 查询已经创建成功的学生信息
		Student student =new Student();
		student = student.find("studentCode=?", sysUser.email).first(); 
		
		//2. 查询 课程信息
		Course course =Course.find("courseName=? ", studentCourses.courseName).first();
		
		//3. 记录区域，学校，专业，课程等信息  
		studentCourses.student=student;
		studentCourses.course=course;
		studentCourses.save();
		
		main();
	}
	
	/**查看个人信息*/
	public static void sysUserInfo(){
		SysUser sysUser = (SysUser)Cache.get(session.getId());
		render(sysUser);
	}
	
	/**修改个人信息*/
	public static void 	modifyUserInfo(@Valid SysUser sysUser){
		sysUser.save(); 
		sysUser=SysUser.find("email=?", sysUser.email).first();
		Cache.set(session.getId(), sysUser);
		sysUserInfo();
	}
	
		/**返回课程列表REST_API*/
	public static void courseList(){ 
		List courses=Course.findAll();
		
		renderJSON(courses);; 
	}
	
		/** TestApi */
	public static void testApi() {
		render();
	}
	
	/**验证码*/
	public static void captcha() {
		Images.Captcha captcha = Images.captcha();
		String code = captcha.getText("#ABCDEF"); 
		flash.put("captcha", code);
        captcha.addNoise();
        captcha.setBackground("#E4EAFD");
        renderBinary(captcha);

	}
	
	/**进入管理员界面*/
	public static void goAdmin(){
		 renderTemplate("Secure/login.html");//定制管理应用，后台管理应用默认首页为安全模块的登陆页面
	}
	
}