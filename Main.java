public class Main {
    public static void main(String[] args) {
        // Distance matrix for 8 cities (Bilbao, 6 regional offices, Vitoria-Gasteiz, in km)
        // Cities: Bilbao, Getxo, Barakaldo, Portugalete, Durango, Castro Urdiales, Amorebieta-Etxano, Vitoria-Gasteiz
        int[][] dist = {
            {0, 15, 11, 13, 34, 35, 25, 66}, // Bilbao
            {15, 0, 5, 7, 45, 48, 30, 70},   // Getxo
            {11, 5, 0, 4, 40, 43, 28, 68},   // Barakaldo
            {13, 7, 4, 0, 42, 45, 30, 69},   // Portugalete
            {34, 45, 40, 42, 0, 60, 50, 40}, // Durango
            {35, 48, 43, 45, 60, 0, 55, 80}, // Castro Urdiales
            {25, 30, 28, 30, 50, 55, 0, 50}, // Amorebieta-Etxano
            {66, 70, 68, 69, 40, 80, 50, 0}  // Vitoria-Gasteiz
        };

        // Create TSP instance and compute minimum distance
        TravelingSalesMan tsp = new TravelingSalesMan(dist);
        int minDistance = tsp.tsp(1, 0); // Start from Bilbao (city 0, mask = 1)
        String route = tsp.getRoute();

        // Map city names to indices for distance lookup
        String[] cityNames = {"Bilbao", "Getxo", "Barakaldo", "Portugalete", "Durango", 
                             "Castro Urdiales", "Amorebieta-Etxano", "Vitoria-Gasteiz"};
        java.util.Map<String, Integer> cityToIndex = new java.util.HashMap<>();
        for (int i = 0; i < cityNames.length; i++) {
            cityToIndex.put(cityNames[i], i);
        }

        // Parse route and calculate segment distances
        String[] cities = route.split(" -> ");
        int totalDistance = 0;
        StringBuilder routeWithDistances = new StringBuilder("Optimal Route:\n");
        for (int i = 0; i < cities.length - 1; i++) {
            String fromCity = cities[i];
            String toCity = cities[i + 1];
            int fromIndex = cityToIndex.get(fromCity);
            int toIndex = cityToIndex.get(toCity);
            int segmentDistance = dist[fromIndex][toIndex];
            totalDistance += segmentDistance;
            routeWithDistances.append(fromCity)
                             .append(" -> ")
                             .append(toCity)
                             .append(" (")
                             .append(segmentDistance)
                             .append(" km)\n");
        }

        // Print results
        System.out.println("Minimum Distance Travelled: " + minDistance + " km");
        System.out.println(routeWithDistances.toString());
        System.out.println("Total Distance: " + totalDistance + " km");
    }
}
