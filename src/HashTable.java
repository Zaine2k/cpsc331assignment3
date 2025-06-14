import java.util.LinkedList;

// Question 2

public class HashTable {
    private static final int LEN = 8;
    private LinkedList<Student>[] table;

    public HashTable() {
        table = new LinkedList[LEN];
        for (int i = 0; i < LEN; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash Function
    public int hashValue(int id) {
        return id % LEN;
    }

    // Search Function
    public boolean search(int id) {
        int index = hashValue(id);
        for (Student s : table[index]) {
            if (s.id == id) {
                return true;
            }
        }
        return false;
    }

    // Retrieve Function
    public String retrieve(int id) {
        int index = hashValue(id);
        for (Student s : table[index]) {
            if (s.id == id) {
                return s.name;
            }
        }
        System.out.println("Can't find student with ID " + id + ".");
        return null;
    }

    // Insert or Update Function
    public void insert(int id, String name) {
        int index = hashValue(id);
        for (Student s : table[index]) {
            if (s.id == id) {
                // Update existing student's name
                s.name = name;
                return;
            }
        }
        // Add new student
        table[index].add(new Student(id, name));
    }

    // Delete Function
    public void delete(int id) {
        int index = hashValue(id);
        for (int i = 0; i < table[index].size(); i++) {
            if (table[index].get(i).id == id) {
                table[index].remove(i);
                System.out.println("Student with ID " + id + " removed.");
                return;
            }
        }
        System.out.println("Can't find Student with ID " + id + ".");
    }

    // toString Function
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LEN; i++) {
            sb.append(i).append(": [");
            for (int j = 0; j < table[i].size(); j++) {
                sb.append(table[i].get(j));
                if (j != table[i].size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}