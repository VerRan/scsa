package models;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Student;
import models.base.BaseUnitTest; 
import play.test.UnitTest;

public class SchoolTest extends BaseUnitTest {

	@Test
	public void testSave() {
		
		//1. 新建地市
		City city = new City("001","西安").save();
		
		//2. 新建学校
		School school = new School();
		school.schoolCode="SC001";
		school.schoolName="西安交通大学"; 
		school.city=city; 
		school.save();
	
		School school2 = new School();
		school2.schoolCode="SC001";
		school2.schoolName="西安外国语大学"; 
		school2.city=city; 
		school2.save();
		
		//3. 统计学校和地市 
		this.assertEquals(1, City.count());
		this.assertEquals(2, School.count());
		
		//4. 查询学校嘻嘻你
		School temp = school.find("schoolCode=? ", "SC001").first();
		this.assertEquals("西安交通大学", temp.schoolName);
		
		List schoolList = school.find("byCity", city).fetch();
		this.assertEquals(2, schoolList.size());
	}


}