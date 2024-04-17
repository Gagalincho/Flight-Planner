package example.com;

public class Flight {
    private String startDestination;
    private String endDestination;
    private Integer passengerCount;

    public Flight(String startDestination, String endDestination, Integer passengerCount) {
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.passengerCount = passengerCount;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }
}
