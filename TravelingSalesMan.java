import java.util.Arrays;

public class TravelingSalesMan {
    private int n; // Number of cities
    private int[][] dist; // Distance matrix
    private int[][] dp; // Memoization table for minimum costs
    private int[][] nextCity; // Table to store the next city in the optimal path
    private int completedVisit; // Bitmask for all cities visited

    // Constructor to initialize the distance matrix and memoization tables
    public TravelingSalesMan(int[][] distanceMatrix) {
        this.n = distanceMatrix.length;
        this.dist = distanceMatrix;
        this.completedVisit = (1 << n) - 1; // All cities visited: 2^n - 1
        this.dp = new int[1 << n][n];
        this.nextCity = new int[1 << n][n]; // Initialize nextCity table
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int[] row : nextCity) {
            Arrays.fill(row, -1);
        }
    }

    // TSP function using dynamic programming with memoization
    public int tsp(int mask, int pos) {
        // Base case: all cities visited, return cost to starting city (Bilbao)
        if (mask == completedVisit) {
            return dist[pos][0];
        }

        // Return memoized result if available
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = Integer.MAX_VALUE;
        int bestNextCity = -1;
        // Try visiting each unvisited city
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) { // City not visited
                int newCost = dist[pos][city] + tsp(mask | (1 << city), city);
                if (newCost < minCost) {
                    minCost = newCost;
                    bestNextCity = city; // Track the city that gives the minimum cost
                }
            }
        }

        // Memoize the result and store the next city
        dp[mask][pos] = minCost;
        nextCity[mask][pos] = bestNextCity;
        return minCost;
    }

    // Method to reconstruct and return the optimal route
    public String getRoute() {
        String[] cityNames = {"Bilbao", "Getxo", "Barakaldo", "Portugalete", "Durango", 
                             "Castro Urdiales", "Amorebieta-Etxano", "Vitoria-Gasteiz"};
        StringBuilder route = new StringBuilder();
        int mask = 1; // Start with only Bilbao visited
        int pos = 0; // Start at Bilbao (city 0)
        route.append(cityNames[pos]).append(" -> ");

        // Reconstruct the path
        while (mask != completedVisit) {
            int next = nextCity[mask][pos];
            if (next == -1) {
                break; // Safety check
            }
            route.append(cityNames[next]).append(" -> ");
            mask |= (1 << next); // Mark next city as visited
            pos = next; // Move to next city
        }
        route.append("Bilbao"); // Return to starting city
        return route.toString();
    }
}
