package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class  Course extends Model {

	@Required(message="课程编码不能为空。")
	public String courseCode;
	@Required(message="课程名称不能为空。")
	public String courseName;
	@Required(message="课程描述不能为空。")
	@MaxSize(500)
	public String courseDesc;
	public Date createDate;
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL)  
	public List<StudentCourses> courses ;//课程
	
 
	public Course() {
	}

	public Course(String courseCode, String courseName, String courseDesc, Date createDate) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.createDate = createDate;
	}

	public String toString(){
		return courseName;
	}

}
