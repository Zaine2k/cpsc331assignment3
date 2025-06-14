import java.util.*;

public class DeliveryRoutes {

    static class E {
        int to, w;
        E(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    // Stores current node (u), distance so far (d), and path taken (p)
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int W = sc.nextInt();
            if (W != 1) throw new IllegalArgumentException("Warehouses must be 1.");

            int L = sc.nextInt();
            int R = sc.nextInt();

            if (L < 0) throw new IllegalArgumentException("Delivery locations must be ≥ 0.");
            if (R < 0) throw new IllegalArgumentException("Roads must be ≥ 0.");

            // total nodes (warehouse is node 0)
            int N = L + 1;

            // Build graph
            Map<Integer, List<E>> g = new HashMap<>();

            for (int i = 0; i < R; i++) {
                int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
                if (u < 0 || u >= N || v < 0 || v >= N)
                    throw new IllegalArgumentException("Invalid node.");
                if (w < 0)
                    throw new IllegalArgumentException("Distance must be ≥ 0.");
                g.computeIfAbsent(u, k -> new ArrayList<>()).add(new E(v, w));
            }

            // Run Dijkstra's algorithm from warehouse (node 0)
            dijkstra(g, N);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
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

            // Explore neighbors
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
            // No path is found
            if (d[i] == Integer.MAX_VALUE) {
                System.out.println("No route exists , Distance : Infinity");
            } else {
                for (int j = 0; j < p[i].size(); j++) {
                    List<Integer> path = p[i].get(j);
                    for (int k = 0; k < path.size(); k++) {
                        System.out.print(path.get(k));
                        if (k != path.size() - 1) System.out.print(" -> ");
                    }
                    if (j != p[i].size() - 1) {
                        System.out.print(" (or) ");
                    }
                }
                System.out.println(", Distance : " + d[i]);
            }
        }
    }
}
