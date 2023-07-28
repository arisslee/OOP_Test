public class ChargingStation {
    private String stationId;
    private String location;
    private double availableCapacityKWh;

    public ChargingStation(String stationId, String location, double availableCapacityKWh) {
        this.stationId = stationId;
        this.location = location;
        this.availableCapacityKWh = availableCapacityKWh;
    }

    //----------------------------------Getters----------------------------------
    public String getStationId() {
        return stationId;
    }
    public String getLocation() {
        return location;
    }
    public double getAvailableCapacityKWh() {
        return availableCapacityKWh;
    }
    
    //----------------------------------Setters----------------------------------  
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailableCapacityKWh(double availableCapacityKWh) {
        this.availableCapacityKWh = availableCapacityKWh;
    }
}
