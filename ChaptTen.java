//@EvanKvalvik

import java.util.*;
import java.lang.*;

public class ChaptTen {
  public static void main(String[]args){
 /*   
    ArrayList<String> hWA = new ArrayList<String>();
    hWA.add("hello");
    hWA.add("World");
    //hWA.add("Again");
    //hWA.add("For");
    //hWA.add("The");
    //hWA.add("Second");
    //hWA.add("Time");
    
    ArrayList<Integer> digits = new ArrayList<Integer>();
    digits.add(2);
    digits.add(4);
    digits.add(5);
    digits.add(1);
    digits.add(3);
 
   doubleList(hWA); //doubles each element
   //minToFront(digits); //puts minimum number to front of list
  // stutter(3, hWA); //dublicates each element (int) many times.
   //filterRange(digits, 3, 4); //removes numbers between first int and second int.
   System.out.println(hWA);
  // System.out.println(digits);
 */ 
  }
  public static void doubleList(ArrayList<String> hWA){ //10.4
    if(hWA== null)
		throw new IllegalArgumentException("ArrayList parameter Can't be null");
    
    int size = hWA.size();
    int i;
    int j = 0; 
    
    for(i=0; i <= size-1; i++){
      hWA.add(i+j, hWA.get(i+j)); //this threw me through a loop so to speak
      j++;
    }
  }
  public static void minToFront(ArrayList<Integer> digits){ //10.6
     if (digits == null) {
        throw new IllegalArgumentException("Parameter 'digits' cannot be null");
    }
    
    int last = 0;
      for(int i = 0; i < digits.size()-1; i++){
        if(digits.get(i) < digits.get(last))
               last = i;
      }
      int digit = digits.get(last);
      digits.remove(last);
      digits.add(0, digit);   
   }
    public static void stutter(int k, ArrayList<String> hWA){ //10.11
     if (hWA == null){
        throw new IllegalArgumentException("Parameter 'hWA' and/or 'k' cannot be null");
    }
    if (k == 0){
        throw new IllegalArgumentException("Parameter 'k' cannot be 0");
    }
    
    ArrayList<String> aux = new ArrayList<String>();
    
    int size = hWA.size();
    int i;
    int j;
 
    for(j = 0; j<= size-1; j++){ 
       for(i = 0; i <= k-1; i++){	 
         aux.add(0, hWA.get(0));
	   }
	   for(i = 0; i < 1; i++){	 
         hWA.remove(0);
	   }
    }
    int secondSize = aux.size();
    for(i = 0; i<= secondSize-1; i++){
      hWA.add(0, aux.get(i));   
    }
  }
  public static void filterRange(ArrayList<Integer> digits, int low, int high){ //10.15
    if (digits ==null){
        throw new IllegalArgumentException("Parameter cannot be null. Check 'hWA', 'high', and 'low'");
    }
    Iterator<Integer> itr = digits.iterator();
    
    while(itr.hasNext()){

      Integer element = itr.next(); 
      if(element >= low && element <= high) {
	    itr.remove();

      }
    }
  }



}
