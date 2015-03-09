import java.lang.reflect.Array;
import java.lang.reflect.Field;

class Person {
	public String name = "Jakub";
	public int age = 15;
	public char test = 'c';
	public boolean student = true;
	public float testfloat = 12.34f;
	public int[] tab = {2, 3, 4};
	public int[][] t = new int[3][3];
	
	public Person(){
		t[0][1] = 10;
		t[2][2] = 5;
 	}
}

class Converter {

	public static String converted;
	
	public static void arrayConvert(Field f, Object a) throws IllegalArgumentException, IllegalAccessException{
		for(int i = 0; i < Array.getLength(a); i++){
			Object arr = Array.get(a, i);
			
			System.out.println(arr.getClass());
			if(arr.getClass().isArray()){
				arrayConvert(f, arr);
			} else if(arr.getClass() == int.class || arr.getClass() == Integer.class || arr.getClass() == short.class || arr.getClass() == long.class || arr.getClass() == float.class || arr.getClass() == double.class){
				converted += arr + ", ";
			} else if(arr.getClass() == String.class || arr.getClass() == char.class || arr.getClass() == boolean.class){
				converted += "\"" + arr + "\", ";
			}
			
		}
	}
	
	public static void toJSON(Object a) throws IllegalArgumentException, IllegalAccessException{
		
		converted = "{\"" + a.getClass().getName() + "\": {";

		for(Field f: a.getClass().getFields()){
			
			//System.out.println(f.getType());
				
			//tablice
			if(!checker(f, a) && f.getType().isArray()){
				
				converted += "\"" + f.getName() + "\":[";
				
				arrayConvert(f, f.get(a));
				
				converted += "], ";
			}
		}
		
		converted += "}}";
		
		System.out.println(converted);
		
	}
	
	public static boolean checker(Field f, Object a) throws IllegalArgumentException, IllegalAccessException{
		
		System.out.println("field: " + f + " object: " + a);
		
		//zmienne liczbowe
		if(f.getType() == int.class || f.getType() == Integer.class || f.getType() == short.class || f.getType() == long.class || f.getType() == float.class || f.getType() == double.class){
			converted += "\"" + f.getName() + "\": " + f.get(a) + ", ";
			//System.out.println(f.get(a));
			return true;
		//zmienne znakowe
		} else if(f.getType() == String.class || f.getType() == char.class || f.getType() == boolean.class){
			converted += "\"" + f.getName() + "\": \"" + f.get(a) + "\", ";	
			return true;
		}
		
		return false;
	}

}
