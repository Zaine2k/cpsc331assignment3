import java.util.LinkedList;
public class Main {

    public static void main(String[] args) {
        HashTable db = new HashTable();

        // Step 1
        db.insert(20500120, "Bob");
        db.insert(20700200, "Alice");
        db.insert(30100230, "Cathy");
        db.insert(20200156, "Ali");

        // Step 2
        System.out.println(db.toString());

        // Step 3
        db.insert(20500120, "Bobby");

        // Step 4
        System.out.println("Search 20500120: " + db.search(20500120));

        // Step 5
        System.out.println("Retrieve 20700200: " + db.retrieve(20700200));

        // Step 6
        db.delete(20700200);

        // Step 7
        db.delete(20700200);

        // Step 8
        db.retrieve(20700200);

        // Step 9
        System.out.println(db.toString());
    }
}