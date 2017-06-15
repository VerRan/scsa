package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;
/***
 * //	Cascade 级联操作 
//	CascadeType. PERSIST 级联持久化 ( 保存 ) 操作 
//	CascadeType. MERGE 级联更新 ( 合并 ) 操作 
//	CascadeType. REFRESH 级联刷新操作，只会查询获取操作 
//	CascadeType. REMOVE 级联删除操作 
//	CascadeType. ALL 级联以上全部操作 
 * 
 * */

@Entity
public class Student extends Model { 
	
	@Required(message="学生编码不能为空。")
	public String studentCode;
	@Required(message="学生名称不能为空。")
	public String studentName;
	@Required(message="性别不能为空。") 
	public String sex;
	@Required(message="年龄不能为空。")
	@Min(value=5,message="学生年龄不能小于5岁。")
	@Max(value=70,message="学生年龄不能大于70岁。")
	public int age;

	@OneToMany(mappedBy="student",cascade=CascadeType.ALL)  
	public List<StudentCourses> courses ;//课程
	
	public Student(){
		
	}
	
	public Student(String studentName, String sex, int age, String studentCode) {
		super();
		this.studentName = studentName;
		this.sex = sex;
		this.age = age;
		this.studentCode = studentCode;
		this.courses=new ArrayList();
	}

	public Student selectCourse(Student student, Course course) {
		StudentCourses selectCourse =  new StudentCourses(student,course).save();
	    courses.add(selectCourse);
	    this.save();
		return this;
	}
	

	public String toString(){
		return studentName;
	}
	

}
