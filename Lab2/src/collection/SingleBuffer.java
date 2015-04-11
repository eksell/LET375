//Author(s): Anton Eksell	& Anna Abri	
//Email:	eksell@student.chalmers.se
//Date: 11/4-2015

package collection;

public class SingleBuffer<E>{
	E[] e;
	int index;
	final int bufferArraySize = 32;

	@SuppressWarnings("unchecked")
	SingleBuffer(){
		e = (E[]) new Object[bufferArraySize];
		index = 0;
	} 

	boolean put(E x){
		if(isFull()){
			return false;
		}else{
			e[index++] = x;
			return true; 
		}
	}

	E get(){
		if(isEmpty()){
			return e[--index];
		}else return null;
	}

	boolean isFull(){
		return bufferArraySize-1 == index;
	}

	boolean isEmpty(){
		return index >= 1;
	}


}
