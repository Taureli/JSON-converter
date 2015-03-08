import java.lang.reflect.Array;
import java.lang.reflect.Field;

class Person {
	public String name = "Jakub";
	public int age = 15;
	public char test = 'c';
	public boolean student = true;
	public float testfloat = 12.34f;
	public int[] tab = {2, 3, 4};
}

public class Converter {

	public static String converted;
	
	public static void toJSON(Object a) throws IllegalArgumentException, IllegalAccessException{
		
		converted = "{\"" + a.getClass().getName() + "\": {";

		for(Field f: a.getClass().getFields()){
			
			//System.out.println(f.getType());
			
			//zmienne liczbowe
			if(f.getType() == int.class || f.getType() == short.class || f.getType() == long.class || f.getType() == float.class || f.getType() == double.class){
				converted += "\"" + f.getName() + "\": " + f.get(a) + ", ";
			//zmienne znakowe
			} else if(f.getType() == String.class || f.getType() == char.class || f.getType() == boolean.class){
				converted += "\"" + f.getName() + "\": \"" + f.get(a) + "\", ";
			//tablice
			} else if(f.getType().isArray()){
				
				converted += "\"" + f.getName() + "\":[";
				
				for (int i = 0; i < Array.getLength(f.get(a)); i++){
					converted += "{\"" + Array.get(f.get(a), i) + "\"},";
				}
				
				converted += "], ";
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
