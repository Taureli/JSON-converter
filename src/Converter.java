import java.lang.reflect.Field;

class Person {
	public String name = "Jakub";
	public int age = 15;
	public char test = 'c';
	public boolean student = true;
	public float testfloat = 12.34f;
}

public class Converter {

	public static String converted;
	
	public static void toJSON(Object a) throws IllegalArgumentException, IllegalAccessException{
		
		converted = "{\"" + a.getClass().getName() + "\": {";

		for(Field f: a.getClass().getFields()){
			//zmienne liczbowe
			if(f.getType() == int.class || f.getType() == short.class || f.getType() == long.class || f.getType() == float.class || f.getType() == double.class){
				converted += "\"" + f.getName() + "\": " + f.get(a) + ", ";
			//zmienne znakowe
			} else if(f.getType() == String.class || f.getType() == char.class || f.getType() == boolean.class){
				converted += "\"" + f.getName() + "\": \"" + f.get(a) + "\", ";
			}
		}
		
		converted += "}}";
		
		System.out.println(converted);
		
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
		Person test = new Person();
		toJSON(test);
	}

}
