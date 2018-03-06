package models;

import org.junit.Test;

import models.base.BaseUnitTest;
import play.test.Fixtures;

public class CityTest extends BaseUnitTest {
 
	@Test
	public void testSave() {
//		City city = new City("CI001","西安"); 
//		city.save();
//		City temp = city.find("cityCode=? ", "CI001").first();
//		this.assertEquals("西安", temp.cityName);
		Fixtures.loadModels("data.yml");
		assertEquals(2,City.count());
	}
}
