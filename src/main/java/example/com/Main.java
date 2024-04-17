package example.com;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the data in a specific format and read all input at once
        //After entering the data press enter one more time because the program will stop reading when it meets an empty line
        System.out.println("Please enter the flight data, followed by the origin, destination and group size: ");
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) { // break the loop if an empty line is encountered
                break;
            }
            lines.add(line);
        }

        // Initialize data structures
        Flight flight;
        List<Flight> flights = new ArrayList<>();
        String origin = "";
        String destination = "";
        int groupSize = 0;

        // Variables to determine when to switch from reading flights to reading origin/destination
        boolean readFlights = true;

        // Parse the input data
        for (String line : lines) {
            if (line.startsWith("Flights:")) {
                continue; // Skip the "Flights:" line
            }
            if (line.startsWith("Origin:")) {
                origin = line.split(":")[1].trim();
                readFlights = false; // Stop reading flights data
                continue;
            }
            if (line.startsWith("Destination:")) {
                destination = line.split(":")[1].trim();
                continue;
            }
            if (line.startsWith("Group size:")) {
                groupSize = Integer.parseInt(line.split(":")[1].trim());
            }

            if (readFlights) {
                String[] parts = line.split(",");
                flight = new Flight(parts[0], parts[1], Integer.parseInt(parts[2]));
                flights.add(flight);
            }
        }

        System.out.println();
        int travelTime = minimalTravelTime(flights, origin, destination, groupSize);
        System.out.println("Minimal travel time: " + travelTime);
    }

    //Breadth First Search Algorithm
    public static int minimalTravelTime(List<Flight> flights, String origin, String destination, int groupSize) {
        Queue<Pair> queue = new ArrayDeque<>();
        Pair pair = new Pair(origin, 0);
        queue.add(pair);

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            String currentCity = current.getCity();
            int currentTime = current.getTravelTime();

            if (currentCity.equals(destination)) {
                return currentTime;
            }

            if(!visited.contains(currentCity)) {
                visited.add(currentCity);
                //All destinations from the current city
                List<String> neighbours = getNeighbours(flights, groupSize, currentCity);

                //Check if the visited Set contains the neighbour and add it in the queue if it doesn't
                for (String neighbour : neighbours) {
                    //If it doesn't contain the neighbour city(end destination of a flight) in the set
                    //add it in the queue as new Pair with current time +1
                    if(!visited.contains(neighbour)) {
                        queue.add(new Pair(neighbour, currentTime + 1));
                    }
                }

            }

        }

        //If destination is unreachable
        return 0;
    }

    public static List<String> getNeighbours(List<Flight> flights, int groupSize, String currentCity) {
        List<String> neighbours = new ArrayList<>();
        //Iterate through all the flights and check if their start destination is the current city
        // and if the group size is equals or bigger than the group size from the input
        for (Flight flight : flights) {
            if (flight.getStartDestination().equals(currentCity) && flight.getPassengerCount() >= groupSize) {
                neighbours.add(flight.getEndDestination());
            }
        }
        return neighbours;
    }
}