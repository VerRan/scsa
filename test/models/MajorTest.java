package models;
 
import org.junit.Test;

import models.base.BaseUnitTest; 

public class MajorTest extends BaseUnitTest {
	
	@Test
	public void test(){
		Major major =new Major();
		major.majorCode="M001";
		major.majorName="计算机科学与技术";
		major.majorDesc="专业描述";
		major.save();
		
		this.assertEquals(1, Major.count());
		
		
	}
}
