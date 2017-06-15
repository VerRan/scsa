package models.base;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BaseUnitTest extends UnitTest {
	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}
	
    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

}
