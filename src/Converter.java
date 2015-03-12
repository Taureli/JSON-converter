import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class Person {
	public String name = "Jakub";
	public int age = 15;
	public char test = 'c';
	public boolean student = true;
	public float testfloat = 12.34f;
	public int[] tab = {2, 3, 4};
	public int[][] t = new int[3][3];
	private String prywatne = "pole";
	static int statyczne = 1234;
	
	public Person(){
		t[0][1] = 10;
		t[2][2] = 5;
 	}
}

class Converter {

	public static String converted;
	
	//Konwersja dla tablic
	public static void arrayConvert(Field f, Object a) throws IllegalArgumentException, IllegalAccessException{
		for(int i = 0; i < Array.getLength(a); i++){
			
			//pobieram element z listy i sprawdzam jego typ
			Object arr = Array.get(a, i);
			
			if(arr.getClass().isArray()){
				arrayConvert(f, arr);
			} else if(arr.getClass() == int.class || arr.getClass() == Integer.class || arr.getClass() == short.class || arr.getClass() == long.class || arr.getClass() == float.class || arr.getClass() == double.class){
				converted += arr + ", ";
			} else if(arr.getClass() == String.class || arr.getClass() == char.class || arr.getClass() == boolean.class){
				converted += "\"" + arr + "\", ";
			}
			
		}		
		
	}
	
	public static void convert(Field f, Object a) throws IllegalArgumentException, IllegalAccessException{
		//Konwersja dla typow prostych i wywolanie metody w przypadku tablic
		if(!checker(f, a) && f.getType().isArray()){				
			converted += "\"" + f.getName() + "\":[";
			
			arrayConvert(f, f.get(a));
			
			//usuniêcie ostatniego przecinka:
			converted = converted.substring(0, converted.length()-2);
			
			converted += "], ";
		}
	}
	
	public static void toJSON(Object a) throws IllegalArgumentException, IllegalAccessException{
		
		converted = "{\"" + a.getClass().getName() + "\": {";

		for(Field f: a.getClass().getDeclaredFields()){
				//Sprawdzenie modyfikatora i ew. ustawienie dostepu
				if(f.getModifiers() == Modifier.PUBLIC){
					convert(f, a);
				} else {
					f.setAccessible(true);
					convert(f, a);
				}
		}
		
		//usuniêcie ostatniego przecinka:
		converted = converted.substring(0, converted.length()-2);
		
		converted += "}}";
		
	}
	
	//Rozroznia typy liczbowe od znakowych
	public static boolean checker(Field f, Object a) throws IllegalArgumentException, IllegalAccessException{
		
		//System.out.println("field: " + f + " object: " + a);
		
		//zmienne liczbowe
		if(f.getType() == int.class || f.getType() == Integer.class || f.getType() == short.class || f.getType() == long.class || f.getType() == float.class || f.getType() == double.class){
			converted += "\"" + f.getName() + "\": " + f.get(a) + ", ";
			return true;
		//zmienne znakowe
		} else if(f.getType() == String.class || f.getType() == char.class || f.getType() == boolean.class){
			converted += "\"" + f.getName() + "\": \"" + f.get(a) + "\", ";	
			return true;
		}
		
		return false;
	}

}
