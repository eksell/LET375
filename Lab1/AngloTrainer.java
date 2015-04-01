// Author(s): Anton Eksell	& Anna Abri
// Email:	eksell@student.chalmers.se
// Date: 29/3-2015
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Arrays;

public class AngloTrainer {

	TreeSet<String> text = new TreeSet<String>();
	TreeSet<String> possibleMatches = new TreeSet<String>();
	int max=0;
	private Scanner scan;

	public AngloTrainer(String dictionaryFile) throws IOException {
		loadDictionary(dictionaryFile);

		dumpDict();
		String rand = randomLetters(max-7);


		System.out.println("\nThe random letters are: "+stringSort(rand)+"\nTry to build as many words from these letters as you can!");


		try {

			int fail = 0;
			Iterator<String> findMatch =  text.iterator();

			//Iterate through the dictionary to collect possible matches
			while(findMatch.hasNext()){
				String findMatchString = findMatch.next();
				//Matches added to a new TreeSet
				if(includes(stringSort(rand),stringSort(findMatchString))){
					possibleMatches.add(findMatchString);
				}
			}

			//While no failure have occurred.
			while(fail == 0){
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = br.readLine();
				
				Iterator<String> posMatch =  text.iterator();

				//Iterate through possible matches
				while(posMatch.hasNext()){
					String posMatchString = posMatch.next();

					if(includes(stringSort(rand),stringSort(posMatchString)) && input.equals(posMatchString)){
						System.out.println("Ok!");
						break; //Target found, break loop.
					}
					else if(!includes(stringSort(rand),stringSort(input))){
						for(int i= 0; i<input.length(); i++){
							String buffer = ""+input.toCharArray()[i];
							if(!includes(stringSort(rand), buffer)){
								fail =1;
							}
						}
						if(fail == 0){
							fail = 2; 
						}
					}
					
					if(!posMatch.hasNext()&& fail == 0){
						fail = 3;
					}
				}
			}

			switch(fail){
			case 1: System.out.println("Your suggestion wasn't built from given letters." ); break;
			case 2: System.out.println("Your suggestion had to many of the given letters." ); break;
			case 3: System.out.print("Your suggestion was not found in the dictionary. " ); break;
			} 

			System.out.println("I found:" );
			for(int i = 0; i<possibleMatches.toArray().length; i++){
				System.out.println(possibleMatches.toArray()[i]);
			}

		}catch (Exception e) {
			System.out.println("Pressing Ctrl+Z threw an exeption and terminated the session,\n your possible answers were:");
			for(int i = 0; i<possibleMatches.toArray().length; i++){
				System.out.println(possibleMatches.toArray()[i]);
			}
		}
	}

	private String stringSort(String a){
		char[] s = a.toCharArray();
		String returnString = ""; 
		Arrays.sort(s);

		for(int i= 0; i< s.length; i++){
			returnString = returnString+s[i];
		}

		//System.out.println(a+"\n "+Arrays.toString(s)+ returnString);

		return returnString;
	}

	// use this to verify loadDictionary
	private void dumpDict() {
		System.out.println(text.toString());
		// Print out the dictionary at the screen.
	}

	private void loadDictionary( String fileName ) {
		File file = new File(fileName);
		try{
			scan = new Scanner(file);
			while( scan.hasNextLine() ){
				String s = scan.nextLine();
				if( max < s.length() ){
					max = s.length();
				}
				text.add(s);

			} 

		}catch(IOException e){ System.out.println("Scanner exception");
		}
		// Read the dictionary into a suitable container.
		// The file is a simple text file. One word per line.
		// ... define!
	}



	private String randomLetters( int length ) {
		// this makes vovels a little more likely
		String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";  
		StringBuffer buf = new StringBuffer(length);
		Random randomGenerator = new Random();
		for ( int i = 0; i < length; i++ ) 
			buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));

		return buf.toString();
	}


	/* Def. includes	
	 * Let #(x,s) = the number of occurrences of the charcter x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 * 
	 * A neccessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b ) {
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;

		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() ) {
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i)) {
				i++; j++;
			} else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}

	// This is just for demonstration purposes.
	@SuppressWarnings({"unused" })
	private void testIncludes() { 
		//                                            expected value
		System.out.println(includes("abc",""));		//t
		System.out.println(includes("","abc"));		//f
		System.out.println(includes("abc","abc"));	//t
		System.out.println(includes("abc","bcd"));	//f
		System.out.println(includes("abc","a"));	//t
		System.out.println(includes("abc","b"));	//t
		System.out.println(includes("abc","c"));	//t
		System.out.println(includes("abc","ab"));	//t
		System.out.println(includes("abc","bc"));	//t
		System.out.println(includes("abc","ac"));	//t
		System.out.println(includes("abc","abcd"));	//f
		System.out.println(includes("abc","abd"));	//f
		System.out.println(includes("abc","d"));	//f
		System.out.println(includes("",""));		//t
		System.out.println(includes("abc","ca"));	//f
		System.out.println(includes("abc","bac"));	//f
		System.out.println(includes("abc","abbc"));	//f
		System.out.println(includes("abbc","abc"));	//t
		System.out.println(includes(null,null));    //t
		System.out.println(includes(null,""));	    //t
		System.out.println(includes(null,"abc"));	//f
		System.out.println(includes("",null));		//t
		System.out.println(includes("abc",null));   //t
	}

	public static void main(String[] args) {	
		try {
			@SuppressWarnings("unused")
			AngloTrainer a = new AngloTrainer("src/dictionary.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}












