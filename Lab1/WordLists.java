//Author(s): Anton Eksell Anton Eksell	& Anna Abri	
//Email:	eksell@student.chalmers.se
//Date: 29/3-2015

import java.io.*;
import java.util.*;


public class WordLists {
	private Reader in = null;
	TreeMap<String,Integer> occur = new TreeMap<String, Integer>();
	TreeMap<Integer, TreeSet<String>> fqOrder = new TreeMap<Integer, TreeSet<String>>();
	File file;
	FileInputStream iStream;
	String input;
	PrintWriter writer; 

	public WordLists(String inputFileName) {

		try {
			file = new File(inputFileName);
			in = new FileReader(file);

		} catch (IOException e) {
			System.out.println("Exception: "+ e.getStackTrace());
		}

	}

	private boolean isPunctuationChar(char c) {
		final String punctChars = ",.:;?!";
		return punctChars.indexOf(c) != -1;
	}

	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}

	private String reverse(String s) {
		char [] in = s.toCharArray();
		int start = 0;
		int end = in.length-1;
		char temp;
		while(end>start){
			temp = in[start];
			in[start] = in[end];
			in[end] = temp;
			end--;
			start++;
		}
		String rev = new String(in);
		return rev; 
	}

	private void computeWordFrequencies() {
		try {
			writer = new PrintWriter("alfaSorted.txt", "UTF-8");

			while((input = this.getWord()) != null){
				if(!occur.containsKey(input)){//Nytt värde
					occur.putIfAbsent(input, 1); 
				}else{//Ökar befintligt värde
					occur.put(input,occur.get(input)+1);
				}
			}
			
			Iterator<String> stringSet = occur.keySet().iterator(); 
			String keyString;
			while(stringSet.hasNext()){
				keyString = stringSet.next(); 
				writer.println(keyString +" "+occur.get(keyString)); 
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Exception: "+ e.getStackTrace());
		}
	}

	private void computeFrequencyMap() {
		try {
			writer = new PrintWriter("frequencySorted.txt", "UTF-8");


			int fq;
			String word = ""; 
			Iterator<String> iter = occur.descendingKeySet().iterator();
			while(iter.hasNext() && word != null){
				word = iter.next().toString();
				fq = occur.get(word);

				if(fqOrder.containsKey(fq)){
					fqOrder.get(fq).add(word);
				}else{
					TreeSet<String> e = new TreeSet<String>();
					e.add(word);
					fqOrder.putIfAbsent(fq, e);
				}
			}

			Iterator<Integer> printIter = fqOrder.descendingKeySet().iterator();

			while(printIter.hasNext()){
				fq = printIter.next();
				Iterator<String> wordSet = fqOrder.get(fq).iterator();
				writer.println(fq+": ");

				while(wordSet.hasNext()){
					String aWord = wordSet.next();
					writer.println("	"+aWord);
				}

			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void computeBackwardsOrder() {

		Set<String> backwardSet = new TreeSet<String>(new ReverseStringComparator());
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("backwardsSorted.txt"));

			for (String s : occur.keySet()){
				backwardSet.add(s);
			}

			for(String s : backwardSet)
				out.write(s+"\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ReverseStringComparator implements Comparator<String> {
		@Override
		public int compare(String arg0, String arg1) {
			return ((reverse(arg0)).compareTo(reverse(arg1)));
		}
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();
		System.out.println("Finished!");

	}

}



















