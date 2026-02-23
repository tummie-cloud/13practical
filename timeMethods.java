import java.lang.Math.*;
import java.io.*;
import java.text.*;

public class timeMethods{
  public static int N = 32654; // Maximum key value
  public static Node[] records; // Array to store the records
  public static Node[] sortedRecords; // Sorted copy for binary search
  public static int[] searchKeys; // Array of 30 random keys to search for

  public static void main(String args[]) {

        // Initialize arrays
        records = new Node[N + 1];
        sortedRecords = new Node[N + 1];
        searchKeys = new int[30];

        DecimalFormat fourD= new DecimalFormat("0.0000");
        DecimalFormart fiveD= new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0 , runTime2= 0 , time;
        double totalTime = 0.0 ;
        int n=N;
        int repetittion , repetitions = 30;

    runTime = 0 ;
    for(repitition = 0; repitition < repititions ; repitition ++ ) {
      start = System.currentTimeMillis();

      for(int i = 0; i < 30; i++) {
          int randomKey = 1 + (int)(Math.random() * 32654);
          linearsearch(array, randomKey);
    }

    finish = System.currentTimeMillis();

    time = (double)(finish - start);
    runTime += time;
    runTime2 += (time*time);
}
    

    
    
  }
}  
  

