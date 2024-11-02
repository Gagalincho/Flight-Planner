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

    public void setCity(String city) {
        this.city = city;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}