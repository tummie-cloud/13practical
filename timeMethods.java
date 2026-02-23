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
        double linearRunTime = 0, linearRunTime2 = 0;
        double binaryRunTime = 0, binaryRunTime2 = 0;
        double time;
        int repetition, repetitions = 30;
        Random rand = new Random();

       // Read data from file
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
                        
                    }
                }
            }
            reader.close();

        
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


  

    
    
  

