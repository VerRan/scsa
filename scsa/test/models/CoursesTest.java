package models;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Student;
import models.base.BaseUnitTest;
import play.test.UnitTest;

public class CoursesTest extends BaseUnitTest {
 
	@Test
	public void testSave() {
		Course course = new Course();
		course.courseCode = "cor001A";
		course.courseName = "数据结构";
		course.courseDesc = "此课程用于教授大二计算机科学与技术专业学生，需要有C语言基础";
		course.createDate = new Date();
		course.save();
		Course temp = course.find("courseCode=? ", "cor001A").first();
		this.assertEquals("数据结构", temp.courseName);
	}

//	@Test
//	public void testSelectCourse() {
//		Student student = new Student("刘恒涛", "男", 32, "ScCode001").save();
//		new Course("CC001", "数据结构", "数据结构", new Date()).save();
//		this.assertEquals(1, Course.count());
//		new Course("CC002", "操作系统原理", "操作系统原理", new Date()).save();
//		this.assertEquals(2, Course.count());
//		
//		
//		List<Course> courses = Course.find("byStudent", student).fetch();
//		this.assertEquals(2, courses.size());
//		Course fisrtCourse = courses.get(0);
//		this.assertEquals(student, fisrtCourse.student);
//		this.assertEquals("数据结构", fisrtCourse.courseName);
//	}
}