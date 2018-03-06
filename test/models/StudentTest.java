package models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Student;
import models.base.BaseUnitTest;
import play.test.Fixtures;
import play.test.UnitTest;

public class StudentTest extends UnitTest {


	
	@Test
	public void selectCourse(){
		 //1. 学生注册
		 Student student = new Student("刘恒涛", "男", 32, "ScCode001").save();
		 Student student2 =  new Student("苗竹青", "男", 32, "ScCode002").save();
		 
		 //2. 新建课程 
		 new Course("CC001", "数据结构", "数据结构", new Date()).save();  
		 new Course("CC002", "操作系统原理", "操作系统原理", new Date()).save();
		 new Course("CC003", "计算机组成原理", "计算机组成原理", new Date()).save();
		
		 //3. 学生选课
		 student.selectCourse(student,Course.find("courseName = ? ", "数据结构").first());//选择数据结构课程
		 student.selectCourse(student,Course.find("courseName = ? ", "操作系统原理").first());//选择操作系统原理课程 
		 
		 student2.selectCourse(student2, Course.find("courseName = ? ", "操作系统原理").first()); //学生2选择课程
		 student2.selectCourse(student2, Course.find("courseName = ? ", "计算机组成原理").first()); //学生2选择课程
		 
		 //4. 统计信息
		 this.assertEquals(2, Student.count());//统计学生数量
		 this.assertEquals(3, Course.count());//统计课程数量
		 
		 //5. 读取刘恒涛选择的课程
		 this.assertEquals(2, StudentCourses.find("byStudent", student).fetch().size()); //从课程中直接查询
		 this.assertEquals(2, student.courses.size()); //从学生侧直接查询选课数量大小
		 List<StudentCourses> selectCoursess =StudentCourses.find("byStudent", student).fetch();
		 StudentCourses selectCourses = selectCoursess.get(0); 
		 this.assertEquals("刘恒涛",selectCourses.student.studentName); //验证是否为刘恒涛
		 this.assertEquals("数据结构",selectCourses.course.courseName);//验证选择课程是否为数据结构
		 
		 
//		 //6. 删除学生1信息
//		 student.delete();
//		 
//		 //7. 检查课程信息被删除
//		 this.assertEquals(1, Student.count());//学生2还存在
//		 this.assertEquals(2, StudentCourses.count());//学生2的课程还存在
//		 this.assertEquals(3, Course.count());//课程不能被删除
	}

}