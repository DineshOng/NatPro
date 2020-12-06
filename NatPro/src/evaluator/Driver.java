package evaluator;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Multimap<String, String> myMultimap = ArrayListMultimap.create();

		  // Adding some key/value
		  myMultimap.put("Fruits", "Bannana");
		  myMultimap.put("Fruits", "Apple");
		  myMultimap.put("Fruits", "Pear");
		  myMultimap.put("Vegetables", "Carrot");
		  myMultimap.put("Fruits", "Carrot");

		  System.out.println(myMultimap);

		  // Getting the size
		  int size = myMultimap.size();
		  System.out.println(size);  // 4

		  // Getting values
		  Collection<String> fruits = myMultimap.get("Fruits");
		  System.out.println(fruits); // [Bannana, Apple, Pear]

		  Collection<String> vegetables = myMultimap.get("Vegetables");
		  System.out.println(vegetables); // [Carrot]
	}

}
