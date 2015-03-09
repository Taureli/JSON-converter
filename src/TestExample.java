import org.junit.Test;


public class TestExample {
	
	@SuppressWarnings("static-access")
	@Test
	public void converterTest() throws IllegalArgumentException, IllegalAccessException{
		Converter c = new Converter();
		Person p = new Person();
		c.toJSON(p);
		System.out.println(c.converted);
		
	}
	
	@Test
	public void errorTest(){
		//assertEquals("Error", c.converted);
	}
	

}
