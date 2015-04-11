package collection;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import static collection.CollectionOps.*;

public class Main {
	public static void main(String[] args) {    
		ArrayList<String> names = new ArrayList<String>();

		List<Integer> heltal = new LinkedList<Integer>();
		heltal.add(1);
		heltal.add(2);
		heltal.add(3);
		heltal.add(4);
		heltal.add(5);

		List<Double> flyttal = new LinkedList<Double>();
		flyttal.add(1.25);
		flyttal.add(3.14);
		flyttal.add(9.7);

		List<String> CampusLindholmen = new LinkedList<String>();
		CampusLindholmen.add("Saga");
		CampusLindholmen.add("Svea");
		CampusLindholmen.add("Jupiter");

		List<String> Johanneberg = new LinkedList<String>();
		Johanneberg.add("HC2");
		Johanneberg.add("ED");
		Johanneberg.add("HC3");

		List<Integer> li1 = new LinkedList<Integer>();
		li1.add(4);
		li1.add(2);
		li1.add(5);
		li1.add(1);
		li1.add(3);

		List<Integer> li2 = new LinkedList<Integer>();
		li2.add(8);
		li2.add(6);
		li2.add(7);
		li2.add(9);

		List<Integer> li3 = new LinkedList<Integer>();
		li3.add(97);
		li3.add(5);
		li3.add(123);
		li3.add(18);


		// Test print for an empty list
		print(names); 
		System.out.println();

		// Test print for a list containing one element
		names.add("a");
		print(names); 
		System.out.println();

		// Test print for a list containing more than one elment
		names.add("b");
		names.add("c");
		print(names); 
		System.out.println();

		System.out.println("Test: "+names.toString());
		System.out.println("Test: "+heltal.toString());
		System.out.println("Test: "+flyttal.toString());
		System.out.println("Test: "+CampusLindholmen.toString());
		// Test the return value from reverse
		CollectionOps.print(reverse(names));
		CollectionOps.print(reverse(heltal));
		CollectionOps.print(reverse(flyttal));
		CollectionOps.print(reverse(CampusLindholmen));
		System.out.println();
		// Test that reverse mutates its argument
		CollectionOps.print(names);
		CollectionOps.print(heltal);
		CollectionOps.print(flyttal);
		CollectionOps.print(CampusLindholmen);
		System.out.println();

		// Assignment 4: Write code to test less here 


		StringComparator stringcomp = new StringComparator();
		IntegerComparator intcomp = new IntegerComparator();

		less(li1,li2,intcomp);
		less(li1,li3,intcomp);
		less(Johanneberg,CampusLindholmen,stringcomp);

		System.out.println("Skall returnera true, false, true."); 

		// Assignment 5: Write code to test map here

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(3);
		l1.add(-42);
		l1.add(88);
		l1.add(19);
		l1.add(-37);
		l1.add(0);
		l1.add(18);

		Collection<Integer>l2 = filter(new isEven(),l1);
		print(l2); // [-42,88,0,18]

		// Assignment 5: Write code to test filter here

         ArrayList<Person> pl = new ArrayList<>();
         pl.add(new Person("Nisse","nisse@hipnet.moc","male",23));
         pl.add(new Person("Lisa","lisa@shipnet.sea","female",67));
         pl.add(new Person("Ada","ada@jahuu.vanu","female",49));
         pl.add(new Person("Kal","karl@gotnet.vg","male",78));
         pl.add(new Person("Beda","beda@fishnet.cod","female",102));
         
         
         // Assignment 6: Write code using lambdas here
         //  epostadresserna till alla kvinnor äldre än 65 år.
               
         ArrayList<String> matureLadiesEmail = new ArrayList<>();
         map(x ->{ if(x.getAge()>= 65 && x.getGender() == "female") return matureLadiesEmail.add(x.getEmail()); else return null;}, pl);
         print(matureLadiesEmail);

		
	}

	public static class StringComparator implements Comparator<String> {
		public int compare(String s1,String s2) {
			return s1.compareTo(s2);
		}
	}

	public static class IntegerComparator implements Comparator<Integer> {
		public int compare(Integer s1,Integer s2) {
			return s1.compareTo(s2);
		}
	}
}
















