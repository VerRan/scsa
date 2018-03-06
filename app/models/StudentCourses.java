package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class StudentCourses extends Model { 
	@ManyToOne
	public Student student; 
	@ManyToOne
	public Course course;
	
	@Required
	public String cityName;
	@Required
	public String schoolName;
	@Required
	public String majorName; 
	@Required
	public String courseName;
	
	public StudentCourses(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	 
}
