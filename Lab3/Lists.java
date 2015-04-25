/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s): Anton Eksell	
 * @version 2015--04-21
 */
public class Lists {

	// Create an empty list (a null terminated list head).
	public static ListNode mkEmpty() {
		return toList("");
	}

	// Returns true if l refers to a null terminated list head, false ow.
	public static boolean isEmpty(ListNode l) {
		if ( l == null )
			throw new ListsException("Lists: null passed to isEmpty");
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
		if ( l == null )
			throw new ListsException("Lists: null passed to copy");
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
		if ( l == null )
			throw new ListsException("Lists: null passed to removeAll");
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
		if(l == null){
			throw new ListsException("Lists: null passed to toString");
		}

		ListNode itr = l;
		String result = "";
		while(true){
			if(itr.next != null){
				itr = itr.next;
				result = result + itr.element;	
			}
			else{
				//  			System.out.println(result);
				break;
			}	
		}
		return result;
	}

	// Testmetod: JunitListTest.testContains()
	public static boolean contains(ListNode l, char c){

		if ( l == null )
			throw new ListsException("Lists: null passed to copy");
		ListNode ptr;



		ptr = l.next;  // f�rsta listelementet i originallistan
		while ( ptr != null ) {
			if(ptr.element==c)
				return true;
			ptr = ptr.next;              // Flytta fram i originallistan
		}
		return false;
	}

	// Testmetod: JunitListTest.testCopyUpperCase()
    public static ListNode copyUpperCase(ListNode l){
    	
        if ( l == null )
            throw new ListsException("Lists: null passed to copy");
        ListNode ptr;
       


        ptr = l.next;  // f?rsta listelementet i originallistan
        
        while ( ptr != null ) {
                 if(ptr.element==ptr.element.toUpperCase())
                ptr.element=ptr.element.toUpperCase();	 
            ptr = ptr.next;              // Flytta fram i originallistan
        }
        return false;
}

	// Testmetod: JunitListTest.testAddFirst()
	public static ListNode addFirst(ListNode l,char c) {  
		return null;
	}

	// This is a private utility method.
	private static ListNode getLastNode(ListNode head) {
		return null;
	}

	// Testmetod: JunitListTest.testAddLast()
	public static ListNode addLast(ListNode l,char c) {
		ListNode itr = l; 
		if (itr == null){
			throw new ListsException("Lists: null passed to addLast");
		}

		else{ 
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
	}

	// Testmetod: JunitListTest.testConcat()
	public static ListNode concat(ListNode l1,ListNode l2) {  
		return null;
	}

	// Testmetod: JunitListTest.testAddAll()
	public static ListNode addAll(ListNode l1,ListNode l2) { 
		if ( l1 == null || l2 == null){
			throw new ListsException("Lists: null passed to addAll");
		}

		return null;
	}

	// Testmetod: JunitListTest.testReverse()
	public static ListNode reverse(ListNode head) {  
		return null;
	}
}
