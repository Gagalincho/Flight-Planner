import example.com.Flight;
import example.com.Main;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightPlannerTest {

    @Test
    public void testNoFlightsAvailable() {
        List<Flight> flights = new ArrayList<>();
        String origin = "SOF";
        String destination = "MLE";
        int groupSize = 5;

        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(0, result, "Expected minimal travel time to be 0 when no flights are available.");
    }

    @Test
    public void testDirectFlightInsufficientCapacity() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("SOF", "MLE", 4));
        String origin = "SOF";
        String destination = "MLE";
        int groupSize = 5;

        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(0, result, "Expected minimal travel time to be 0 due to insufficient capacity.");
    }

    @Test
    public void testDirectFlightSufficientCapacity() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("SOF", "MLE", 5));
        String origin = "SOF";
        String destination = "MLE";
        int groupSize = 5;

        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(1, result, "Expected minimal travel time to be 1 with sufficient capacity.");
    }

    @Test
    public void testMultipleConnectionsAllViable() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("SOF", "IST", 5));
        flights.add(new Flight("IST", "CMB", 5));
        flights.add(new Flight("CMB", "MLE", 5));
        String origin = "SOF";
        String destination = "MLE";
        int groupSize = 4;

        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(3, result, "Expected minimal travel time to be 3 with all connections viable.");
    }

    @Test
    public void testDestinationUnreachableDueToInsufficientGroupSizeAlongPath() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("SOF", "IST", 3));
        flights.add(new Flight("IST", "CMB", 3));
        flights.add(new Flight("CMB", "MLE", 3));
        String origin = "SOF";
        String destination = "MLE";
        int groupSize = 4;

        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(0, result, "Expected minimal travel time to be 0 as the destination is unreachable due to insufficient group size.");
    }

    @Test
    public void testEfficientPathFindingWithMultipleOptions() {
        List<Flight> flights = new ArrayList<>();
        // Shortest path
        flights.add(new Flight("A", "D", 10));
        flights.add(new Flight("D", "G", 10));
        flights.add(new Flight("G", "J", 10));

        // Additional longer paths
        flights.add(new Flight("A", "B", 10));
        flights.add(new Flight("B", "C", 10));
        flights.add(new Flight("C", "D", 10));
        flights.add(new Flight("D", "E", 10));
        flights.add(new Flight("E", "F", 10));
        flights.add(new Flight("F", "G", 10));
        flights.add(new Flight("G", "H", 10));
        flights.add(new Flight("H", "I", 10));
        flights.add(new Flight("I", "J", 10));

        String origin = "A";
        String destination = "J";
        int groupSize = 5;

        // MinimalTravelTime method should find the shortest path efficiently
        int result = Main.minimalTravelTime(flights, origin, destination, groupSize);
        assertEquals(3, result, "Expected minimal travel time to be 3 hours for the shortest path from A to J.");
    }
}
