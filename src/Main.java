import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Question 2
        HashTable db = new HashTable();

        // Step 1
        // Insert the students with the following information:
        // ”20500120:Bob”, ”20700200:Alice”, ”30100230:Cathy”, and ”20200156:Ali”.
        db.insert(20500120, "Bob");
        db.insert(20700200, "Alice");
        db.insert(30100230, "Cathy");
        db.insert(20200156, "Ali");

        // Step 2
        // Call the ToString function to print the entire database.
        System.out.println(db.toString());

        // Step 3
        // Insert ”20500120:Bobby” to update Bob’s name
        db.insert(20500120, "Bobby");

        // Step 4
        // Call the Search function for the ID ”20500120”
        System.out.println("Search 20500120: " + db.search(20500120));

        // Step 5
        // Retrieve the value associated with the ID ”20700200”.
        System.out.println("Retrieve 20700200: " + db.retrieve(20700200));

        // Step 6
        // Remove the student with the ID ”20700200”.
        db.delete(20700200);

        // Step 7
        // Again call the Remove function with the ID ”20700200”. (Error Handling: Should
        // print an appropriate message)
        db.delete(20700200);

        // Step 8
        // Now call the Retrieve function with the ID ”20700200”. (Error Handling: Should
        // print an appropriate message)
        db.retrieve(20700200);

        // Step 9
        // Call the ToString function again to print the updated database.
        System.out.println(db.toString());


        // Question 3
        HeapSort hs = new HeapSort();

        // Generate Arrays

        // Random array w/ size 1000
        Random rand = new Random();
        int[] randomArr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            randomArr[i] = rand.nextInt(10000);
        }

        // Sorted array w/ size 1000
        int[] sortedArr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            sortedArr[i] = i;
        }

        System.out.println("1 -> Heapify Swap Count");
        System.out.println("2 -> Total Swap Count");

        // Test arrays
        System.out.println("Random Array Results:");
        hs.run(randomArr);

        System.out.println("Sorted Array Results:");
        hs.run(sortedArr);


        // Question 4

    }
}