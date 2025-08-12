This program is a scalability test for the Traveling Salesman Problem (TSP) solver implemented using dynamic programming with bitmasking. 
The main idea is to measure how execution time grows as the number of cities increases. It works by generating random distance matrices for
city counts ranging from 5 to 15. For each city count n, the program creates an n × n matrix where the diagonal values are zero 
(distance from a city to itself), and other entries are random integers between 1 and 100, representing distances between different cities.

For each generated matrix, the program calls the TravelingSalesMan class to compute the minimum travel distance starting from city 0,
visiting all cities exactly once, and returning to the start. The tsp() method applies a bitmask-based memoization approach to reduce redundant calculations,
and the execution time is measured in milliseconds. The results are printed for each n, showing the number of cities, optimal distance found, and time taken,
allowing developers to observe how the algorithm’s performance scales with problem size.
