
import java.lang.Math.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class timeMethods {
   
    public static int N = 32654;
    public static Node[] records;
    public static Node[] sortedRecords;
    public static int[] searchKeys;
   
    public static void main(String args[]) {
       
        // Initialize arrays
        records = new Node[N + 1];
        sortedRecords = new Node[N + 1];
        searchKeys = new int[30];
       
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");
       
        long start, finish;
        double linearRunTime = 0, linearRunTime2 = 0;
        double binaryRunTime = 0, binaryRunTime2 = 0;
        double time;
        int repetition, repetitions = 30;
        Random rand = new Random();
       
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ulysses.numbered"));
            String line;
           
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 5) {
                    try {
                        int key = Integer.parseInt(line.substring(0, 5).trim());
                        String data = line.substring(5);
                        if (key >= 1 && key <= N) {
                            records[key] = new Node(key, data);
                        }
                    } catch (NumberFormatException e) {
                        // Skip lines that don't start with a valid number
                    }
                }
            }
            reader.close();
           
            // Fill any missing keys
            for (int i = 1; i <= N; i++) {
                if (records[i] == null) {
                    records[i] = new Node(i, "");
                }
                sortedRecords[i] = records[i];
            }
           
            // Sort for binary search (excluding index 0)
            Arrays.sort(sortedRecords, 1, N + 1, (a, b) -> Integer.compare(a.key, b.key));
           
        } catch (FileNotFoundException e) {
            System.out.println("Error: ulysses.numbered file not found!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
       
        
        for (int j = 0; j < 100; j++) {
            int key = rand.nextInt(N) + 1;
            linearsearch(key, records);
            binarysearch(key, sortedRecords);
        }
       
        // Main experiment loop
        for(repetition = 0; repetition < repetitions; repetition++) {
           
            // Generate 30 random keys for this repetition
            for (int j = 0; j < 30; j++) {
                searchKeys[j] = rand.nextInt(N) + 1;
            }
           
            // Time 30 linear searches
            start = System.currentTimeMillis();
            performLinearSearch();
            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            linearRunTime += time;
            linearRunTime2 += (time * time);
           
            // Time 30 binary searches
            start = System.currentTimeMillis();
            performBinarySearch();
            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            binaryRunTime += time;
            binaryRunTime2 += (time * time);
        }
       
        // Calculate statistics for linear search
        double linearAveRuntime = linearRunTime / repetitions;
        double linearStdDeviation = Math.sqrt((linearRunTime2 - repetitions * linearAveRuntime * linearAveRuntime) / (repetitions - 1));
       
        // Calculate statistics for binary search
        double binaryAveRuntime = binaryRunTime / repetitions;
        double binaryStdDeviation = Math.sqrt((binaryRunTime2 - repetitions * binaryAveRuntime * binaryAveRuntime) / (repetitions - 1));
       
        // FINAL OUTPUT - exactly four numbers as required
        System.out.println(fiveD.format(linearAveRuntime) + " " +
                           fourD.format(linearStdDeviation) + " " +
                           fiveD.format(binaryAveRuntime) + " " +
                           fourD.format(binaryStdDeviation));
    }
   
    // Perform 30 linear searches using the pre-generated keys
    static void performLinearSearch() {
        for (int i = 0; i < 30; i++) {
            linearsearch(searchKeys[i], records);
        }
    }
   
    // Linear search for a single key
    static void linearsearch(int key, Node[] arr) {
        for (int i = 1; i <= N; i++) {
            if (arr[i].key == key) {
                return;
            }
        }
    }
   
    // Perform 30 binary searches using the pre-generated keys
    static void performBinarySearch() {
        for (int i = 0; i < 30; i++) {
            binarysearch(searchKeys[i], sortedRecords);
        }
    }
   
    // Binary search for a single key
    static void binarysearch(int key, Node[] arr) {
        int left = 1;
        int right = N;
       
        while (left <= right) {
            int mid = left + (right - left) / 2;
           
            if (arr[mid].key == key) {
                return;
            }
            if (arr[mid].key < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}

// Node class to store key-value pairs
class Node {
    int key;
    String data;
   
    Node(int k, String d) {
        key = k;
        data = d;
    }
}
  

    
    
  

