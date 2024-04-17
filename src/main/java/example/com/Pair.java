package example.com;

class Pair {
    private String city;
    private int travelTime;

    Pair(String city, int travelTime) {
        this.city = city;
        this.travelTime = travelTime;
    }

    public String getCity() {
        return city;
    }

    public int getTravelTime() {
        return travelTime;
    }
}
