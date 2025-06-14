import java.util.*;

// Assignment 3 CPSC 331
// Zaine Ancheta
// UCID 30214484
// Contains questions 2, 3 and 4 with their tests.
// All references will be listed above the question they were used for.

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Question 2
        // References Used:
        // https://www.geeksforgeeks.org/hashing-data-structure/
        // Hash Table | Simple Explanation + Java Implementation | Bro Code https://www.youtube.com/watch?v=KyUTuwz_b7Q

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
        // References Used:
        // https://www.geeksforgeeks.org/heap-sort/
        // https://www.baeldung.com/java-heap-sort
        // Heapsort implementation in java | iflet https://www.youtube.com/watch?v=WCEeum-qCtI
        // Used ChatGPT to debug question 3's Main for testing.

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
        // References Used:
        // https://www.geeksforgeeks.org/java/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
        // https://www.baeldung.com/java-dijkstra
        // https://stackoverflow.com/questions/35546147/implementation-of-dijkstra-algorithm-in-java
        // https://stackoverflow.com/questions/48921019/djikstras-algorithm-using-adjacency-list-and-priority-queue-in-java
        // Dijkstra's Algorithm Explained and Implemented in Java Geekific: https://www.youtube.com/watch?v=BuvKtCh0SKk
        // Used ChatGPT for debugging in question 4.

        System.out.println("--------------------------------- ");
        System.out.println("Enter Question 4 input:");
        runQuestion4();

        System.out.println("Question 4 complete.");
    }

    static void runQuestion4() {
        try {
            int W = sc.nextInt();
            if (W != 1) throw new IllegalArgumentException("Warehouses must be 1.");

            int L = sc.nextInt();
            int R = sc.nextInt();

            if (L < 0) throw new IllegalArgumentException("Delivery locations must be ≥ 0.");
            if (R < 0) throw new IllegalArgumentException("Roads must be ≥ 0.");

            int N = L + 1;

            Map<Integer, List<E>> g = new HashMap<>();

            for (int i = 0; i < R; i++) {
                int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
                if (u < 0 || u >= N || v < 0 || v >= N)
                    throw new IllegalArgumentException("Invalid node.");
                if (w < 0)
                    throw new IllegalArgumentException("Distance must be ≥ 0.");
                g.computeIfAbsent(u, k -> new ArrayList<>()).add(new E(v, w));
            }

            dijkstra(g, N);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static class E {
        int to, w;
        E(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class S implements Comparable<S> {
        int u, d;
        List<Integer> p;
        S(int u, int d, List<Integer> p) {
            this.u = u;
            this.d = d;
            this.p = p;
        }
        public int compareTo(S o) {
            return Integer.compare(this.d, o.d);
        }
    }

    static void dijkstra(Map<Integer, List<E>> g, int N) {
        int[] d = new int[N];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;

        List<List<Integer>>[] p = new List[N];
        for (int i = 0; i < N; i++) p[i] = new ArrayList<>();
        p[0].add(new ArrayList<>(Arrays.asList(0)));

        PriorityQueue<S> pq = new PriorityQueue<>();
        pq.add(new S(0, 0, new ArrayList<>(Arrays.asList(0))));

        while (!pq.isEmpty()) {
            S cur = pq.poll();
            int u = cur.u, distU = cur.d;
            List<Integer> pathU = cur.p;

            if (distU > d[u]) continue;

            if (g.containsKey(u)) {
                for (E e : g.get(u)) {
                    int v = e.to, w = e.w;
                    if (d[u] + w < d[v]) {
                        d[v] = d[u] + w;
                        p[v].clear();
                        List<Integer> newPath = new ArrayList<>(pathU);
                        newPath.add(v);
                        p[v].add(newPath);
                        pq.add(new S(v, d[v], newPath));
                    } else if (d[u] + w == d[v]) {
                        List<Integer> newPath = new ArrayList<>(pathU);
                        newPath.add(v);
                        p[v].add(newPath);
                        pq.add(new S(v, d[v], newPath));
                    }
                }
            }
        }

        // Output
        for (int i = 1; i < N; i++) {
            System.out.print("Delivery Location " + i + " - Shortest Route : ");
            if (d[i] == Integer.MAX_VALUE) {
                System.out.println("No route exists , Distance : Infinity");
            } else {
                for (int j = 0; j < p[i].size(); j++) {
                    if (j == 0) {
                        List<Integer> path = p[i].get(j);
                        for (int k = 0; k < path.size(); k++) {
                            System.out.print(path.get(k));
                            if (k != path.size() - 1) System.out.print(" -> ");
                        }
                    } else {
                        System.out.print(" (or ");
                        List<Integer> path = p[i].get(j);
                        for (int k = 0; k < path.size(); k++) {
                            System.out.print(path.get(k));
                            if (k != path.size() - 1) System.out.print(" -> ");
                        }
                        System.out.print(")");
                    }
                }
                System.out.println(", Distance : " + d[i]);
            }
        }
    }
}