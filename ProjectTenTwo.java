//@EvanKvalvik

import java.util.*;
import java.io.*;

public class ProjectTenTwo { //at least it compiles
  public static void main (String[]args) throws FileNotFoundException{
    
    File file = new File("reversed.txt");
    Scanner scan = new Scanner(file);
    //String str = Scan.nextString();
    ArrayList<String> hWA = new ArrayList<>();
    
    while (scan.hasNextLine()) {
    hWA.add(scan.nextLine());
    }
    
    System.out.println(hWA);
    reverse(hWA);
   // reverseTwo(hWA);
    System.out.println(hWA);

  }
  public static void reverse(ArrayList<String> hWA){
    ArrayList<String> aux = new ArrayList<String>();
    
    int size = hWA.size();
 
    int i;
    int j;
 
  for(j = 0; j<= size-1; j++){
    aux.add(0, hWA.get(0));
    for(i = 0; i < 1; i++){	 
      hWA.remove(0);
    }
    }
  hWA.addAll(aux); 
  }
}
