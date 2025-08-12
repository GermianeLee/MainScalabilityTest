import java.util.Random;

public class MainScalabilityTest {
    public static void main(String[] args) {
        Random rand = new Random();
        for (int n = 5; n <= 15; n++) {
            // Generate random distance matrix
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = rand.nextInt(100) + 1; // Random distance 1-100 km
                    }
                }
            }

            // Measure execution time
            long startTime = System.nanoTime();
            TravelingSalesMan tsp = new TravelingSalesMan(dist);
            int minDistance = tsp.tsp(1, 0);
            long endTime = System.nanoTime();
            double durationMs = (endTime - startTime) / 1_000_000.0;

            System.out.printf("Cities: %d, Min Distance: %d km, Execution Time: %.2f ms%n",
                    n, minDistance, durationMs);
        }
    }
}
