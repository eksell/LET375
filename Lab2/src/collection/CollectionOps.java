//Author(s): Anton Eksell	& Anna Abri	
//Email:	eksell@student.chalmers.se
//Date: 11/4-2015


package collection;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import java.util.function.Function;

public class CollectionOps {

	// Put your code for print here ...

	public static <T> void print(Collection<T> l){
		Iterator<T> it = l.iterator();
		String s ="["; 

		while(it.hasNext()){
			s=s+it.next().toString();
			if(it.hasNext()){
				s = s+",";
			}
		}
		s = s+"]";

		System.out.println(s);

	}

	// Put your code for reverse here ...

	public static <T> List<T> reverse(List<T> l){
		Iterator<T> it = l.iterator();
		SingleBuffer<T> sB = new SingleBuffer<T>();

		while(it.hasNext()){
			T element = it.next();
			sB.put(element);
		}

		l.clear();

		T str;
		while((str = sB.get()) != null){
			l.add(str);
		}

		return l;
	}

	// Put your code for less here ...

	public static <T> boolean less(Collection<T> c1, Collection<T> c2, Comparator<T> comp){
		Iterator<T> first = c1.iterator();
		boolean strictlyLess = true;

		while(first.hasNext()){
			T one = first.next();
			Iterator<T> sec = c2.iterator();
			while(sec.hasNext()){
				T two = sec.next();
				//System.out.println("CompResultat: "+one+" "+two+" "+comp.compare(one, two));
				if(-1<comp.compare(one, two)){
					strictlyLess = false;
				}
			}
		}
		System.out.println("Resultat: "+strictlyLess);
		return strictlyLess;
	}

	// Example
	public static <T,R> Collection<R>
	map(Function<T,R> f,Collection<T> c) 
	{
		// Determine the dynamic type of the collection
		@SuppressWarnings("rawtypes")
		Class<? extends Collection> cls = c.getClass();
		try {
			// Create an object of the same dynamic type as c
			@SuppressWarnings("unchecked")
			Collection<R> result = (Collection<R>)cls.newInstance();
			// type.cast(type.newInstance());
			// Copy the elements and apply op to them
			for ( T x : c )
				result.add(f.apply(x));
			return result;   
		}   
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Put your code for filter here ...
	
	public static <T> Collection<T>
	filter(Predicate<T> f,Collection<T> c) 
	{
		// Determine the dynamic type of the collection
		@SuppressWarnings("rawtypes")
		Class<? extends Collection> cls = c.getClass();
		try {
			// Create an object of the same dynamic type as c
			@SuppressWarnings("unchecked")
			Collection<T> result = (Collection<T>)cls.newInstance();
			// type.cast(type.newInstance());
			// Copy the elements and apply op to them
			for ( T x : c )
				if(f.test(x)){
					result.add(x);
			}
			//print(result);	
			return result;   
		}   
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
