import java.io.ObjectInputStream.GetField;

/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s): Anton Eksell, Anna Abri
 * @version 2015-04-21
 */
public class Lists {

	// Create an empty list (a null terminated list head).
	public static ListNode mkEmpty() {
		return toList("");
	}

	// Returns true if l refers to a null terminated list head, false ow.
	public static boolean isEmpty(ListNode l) {
		Exception(l, "isEmpty");
		return l.next == null;
	}

	// Two lists are equal if both are empty, or if they have equal lengths
	// and contain pairwise equal elements at the same positions.
	public static boolean equals(ListNode l1,ListNode l2) {
		if ( isEmpty(l1) && isEmpty(l2) )
			return true;
		else if ( isEmpty(l1) || isEmpty(l2) )
			return false;
		else { // both lists are non-empty
			ListNode p1 = l1.next, p2 = l2.next;
			while ( p1 != null && p2 != null ) {
				char c1 = p1.element, c2 = p2.element;
				if ( p1.element != p2.element )
					return false;
				p1 = p1.next;
				p2 = p2.next;
			}
			return p1 == null && p2 == null;
		}
	}

	// Se forel. OH
	public static ListNode toList(String chars) {
		ListNode head, ptr1;     // head pekar alltid pa listans huvud
		head = new ListNode();   // Listans huvud (innehaller ej data)
		head.next = null;
		ptr1 = head;             // ptr pekar pa sista noden

		// Bygg en lista av tecken
		for ( int i = 0; i < chars.length(); i++ ) {
			ptr1.next = new ListNode();          // Addera en ny nod sist
			ptr1 = ptr1.next;                    // Flytta fram till den nya noden
			ptr1.element = chars.charAt(i);      // Satt in tecknet
			ptr1.next = null;                    // Avsluta listan
		} 
		return head;
	}

	// Se forel. OH
	public static ListNode copy(ListNode l) {
		Exception(l, "copy");

		ListNode head,ptr1,ptr2;
		head = new ListNode();             // Kopian
		head.next = null;
		ptr1 = head;

		ptr2 = l.next;  // forsta listelementet i originallistan
		while ( ptr2 != null ) {
			ptr1.next = new ListNode();    // Ny nod i kopian
			ptr1 = ptr1.next;              // Flytta fram
			ptr1.element = ptr2.element;   // Kopiera tecknet
			ptr1.next = null;              // Avsluta
			ptr2 = ptr2.next;              // Flytta fram i originallistan
		}
		return head;
	}

	// Se forel. OH
	public static ListNode removeAll(ListNode l,char c) {
		Exception(l, "removeAll");
		ListNode p = l;
		while ( p.next != null ) {
			ListNode temp = p.next;      // Handtag pa nasta nod
			if ( temp.element == c )     // Skall den tas bort?
				p.next = temp.next;      // Lanka forbi
			else
				p = p.next;              // Nej, ga vidare *
		}
		// * p far ej flyttas om den efterfoljande noden togs bort!
		return l;
	}

	// ---------------- Uppgifter ----------------- 

	// Testmetod: JunitListTest.testToString()
	public static String toString(ListNode l) {
		Exception(l, "toString");

		ListNode itr = l;
		String result = "";
		while(true){
			if(itr.next != null){
				itr = itr.next;
				result = result + itr.element;	
			}
			else{
				break;
			}	
		}
		return result;
	}

	// Testmetod: JunitListTest.testContains()
	public static boolean contains(ListNode l, char c){
		Exception(l,"contains");

		ListNode ptr;

		ptr = l.next;  // fï¿½rsta listelementet i originallistan
		while ( ptr != null ) {
			if(ptr.element==c)
				return true;
			ptr = ptr.next;              // Flytta fram i originallistan
		}
		return false;
	}

	// Testmetod: JunitListTest.testCopyUpperCase()

	//     public static ListNode copyUpperCase(ListNode l){
	//
	//    	 if ( l == null )
	//            throw new ListsException("Lists: null passed to copy");
	//        ListNode head,ptr1,ptr2;
	//		head = new ListNode();             // Kopian
	//		head.next = null;
	//		ptr1 = head;
	//
	//		ptr2 = l.next;  // forsta listelementet i originallistan
	//		while ( ptr2 != null ) {
	//			ptr1.next = new ListNode();    // Ny nod i kopian
	//			ptr1 = ptr1.next; //flytta fram
	//				if(Character.isUpperCase(ptr2.element)) {
	//					ptr1.element = ptr2.element;   // Kopiera tecknet
	//					ptr1.next = null;              // Avsluta
	//					ptr2 = ptr2.next;              // Flytta fram i originallistan
	//				}
	//		}
	//		return head;
	//	}

	// Testmetod: JunitListTest.testAddFirst()
	public static ListNode addFirst(ListNode l,char c) {
		//Adderar c först i l. Metoden muterar l och returnerar en referens till l.
		//Gör antagandet att l är head i listan och därför tomt.
		Exception(l, "addFirst");
		ListNode head = new ListNode();
		l.element = c; 
		head.next = l;
		
		return head;
	}

	// This is a private utility method.
	private static ListNode getLastNode(ListNode head) {
		//Returnerar en referens till den sista noden i l (listhuvudet om l refererar till en tom lista.)
		//Metoden muterar ej l.

		return null;
	}

	// Testmetod: JunitListTest.testAddLast()
	public static ListNode addLast(ListNode l,char c) {
		Exception(l, "addLast");

		ListNode itr = l; 
		while(true){
			if(itr.next == null){
				itr.next = new ListNode();
				itr.next.element = c;
				break;
			}
			else{
				itr = itr.next;
			}
		}
		return l;
	}

	// Testmetod: JunitListTest.testConcat()
	public static ListNode concat(ListNode l1,ListNode l2) {
		ListNode ptr;
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;
			else{
				ptr=l1;
				while(ptr.next != null){
					ptr = ptr.next;
					
				}
				ptr.next = l2;
			}
		
		return l1;
	}

	// Testmetod: JunitListTest.testAddAll()
	public static ListNode addAll(ListNode l1,ListNode l2) {
		Exception(l1, "addAll");
		Exception(l2, "addAll");
		
		getLastNode(l1).next = l2;

		return l1;
	}

	// Testmetod: JunitListTest.testReverse()
	public static ListNode reverse(ListNode head) {
		Exception(head, "reverse");
		//TODO
		return null;
	}

	private static void Exception(ListNode l, String s){
		if ( l == null){
			throw new ListsException("Lists: null passed to "+s);
		}
	}
}
