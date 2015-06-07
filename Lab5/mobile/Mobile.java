package mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Anna Abri and Anton Eksell group 21
 * @version 2015-05-13
 *
 */

public class Mobile {
	
	private enum MobileType { SIMPLE, COMPOSITE }
	private MobileType type;
	private float weight;                   // Simple
	private float leftLength, rightLength;  // Composite
	private Mobile left, right;
	
	public Mobile( float weight ) {
		type = MobileType.SIMPLE;
		this.weight = weight;
		left = null;
		right = null;
		
	}
	
	public Mobile( Mobile left, float leftLength, Mobile right, float rightLength ) {
		type = MobileType.COMPOSITE;
		this.left = left;
		this.right = right;
	    this.leftLength = leftLength;
	    this.rightLength = rightLength;	
	}
	
	// Return the total mass of the mobile
	public float getWeight() {
		if ( isSimple() )
			return weight;
		else
			return left.getWeight() + right.getWeight();
	}  
	
	// Return the maximum height of the mobile
	public int getHeight() {
		
		if(isSimple()) //basfall
			return 1;
	
		else{
		int hl = left.getHeight();
		int hr = right.getHeight();
		return Math.max(hl, hr) + 1;
		
		
		
			
		}	
		 
	
	}  
	
	// Print the leaves of the mobile
	public void flatten()  {
		
	      if(isSimple()){ //basfall
	      System.out.print(getWeight()+" ");
	      }
	      
	      else{
	    left.flatten();	  
	    right.flatten();
	      
	      }
	}
	      
	  
	
//	Print a structured view of the mobile
	public void prettyPrint() {
		if(isSimple()){ //basfall
		      System.out.print("("+getWeight()+")");
		      }
		      
		      else{
		    	  
		    System.out.print("[");
		    left.prettyPrint();
		    System.out.print(",");
		    System.out.print(leftLength);
		    System.out.print(",");
		    right.prettyPrint();
		    System.out.print(",");
		    System.out.print(rightLength);
		    System.out.print("]");
		      }
	      
	}
	
// Determine if the mobile is balanced
	public boolean isBalanced() {
		final double eps = 0.000001;
		return isSimple() ||
		    left.isBalanced() && right.isBalanced() &&
		        Math.abs( leftLength * left.getWeight() -
				rightLength * right.getWeight() ) < eps;
	}   

// Determine if two mobiles are equal	
	public boolean equals(  Mobile rhs ) {
		double eps = 0.0000001;
		Mobile m = (Mobile) rhs;
		
		if(!(rhs instanceof Mobile))
			return false;
			
		
	    if(isSimple() != m.isSimple())
	    	return false;
	    
	    if(isSimple())
			return Math.abs(getWeight() - m.getWeight()) < eps;
	    else 
			return (leftLength - m.leftLength) < eps && (rightLength - m.rightLength) < eps
					&& left.equals(m.left) && right.equals(m.right);
			
	    
	    	
	}
	
//	Return a clone of this mobile
	public Mobile clone() {
         if(isSimple())
        	 return new Mobile(weight);
         return new Mobile(left.clone(), leftLength, right.clone(), rightLength);
      
	}
	
// Change this mobile to its mirror image
	public void mirror() {
         if(isSimple())
        	 return;
         else{
        	 right.mirror();
        	 left.mirror();
        	 
        	 Mobile tempMobile = right;
        	 float tempLength = rightLength;
        	 right = left;
        	 rightLength = leftLength;
        	 left = tempMobile;
        	 leftlength = tempLength;
        	 
        			
         }
	}
	
	private boolean isSimple() { 
		return type == MobileType.SIMPLE; 
	}
	
	public static void main(String[] args) {
		Mobile  m1 = new Mobile( 1 ),
		        m2 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		        m = new Mobile( m1, 10, m2, 2 );
	
		System.out.println("Total mass: " + m.getWeight() );

		System.out.println("Height:     " + m.getHeight() );
		m.flatten(); System.out.println();
		m.prettyPrint(); System.out.println();
		if ( m.isBalanced() )
			System.out.println("Balanced!");
		else
			System.out.println("Not balanced!");
		
		Mobile m22 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		       m3 = new Mobile( m1, 10, m22, 2 );
		if ( m.equals(m3) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");
		
		Mobile c = m.clone();
		if ( c.equals(m) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");

		if ( c == m )
			System.out.println("Identical!");	// They should definately not!
		else
			System.out.println("Not identical!");
		
		m.mirror();
		m.prettyPrint(); System.out.println();
		m.mirror();
		m.prettyPrint(); System.out.println();

	}
}
