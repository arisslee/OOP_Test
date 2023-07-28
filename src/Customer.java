import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String customerId;
    private String name;
    private Map<String, String> ownedCars; // CarModelId -> CarModelName
    private Map<String, ChargingStation> chargingStations; // CarModelId -> ChargingStation

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.ownedCars = new HashMap<>();
        this.chargingStations = new HashMap<>();
    }

    //----------------------------------Getters----------------------------------
    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public Map<String, String> getOwnedCars() {
        return ownedCars;
    }
    public Map<String, ChargingStation> getChargingStations() {
        return chargingStations;
    }

    //----------------------------------Setters----------------------------------
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwnedCars(Map<String, String> ownedCars) {
        this.ownedCars = ownedCars;
    }
    public void setChargingStations(Map<String, ChargingStation> chargingStations) {
        this.chargingStations = chargingStations;
    }
    public void addOwnedCar(String carModelId, String carModelName) {
        ownedCars.put(carModelId, carModelName);
    }
    public void addChargingStation(String carModelId, ChargingStation chargingStation) {
        chargingStations.put(carModelId, chargingStation);
    }
}