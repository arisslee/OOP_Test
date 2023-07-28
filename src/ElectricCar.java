public class ElectricCar {
    private int id;
    private String model;
    private String manufacturer;
    private ChargingStation chargingStation;
    private double batteryCapacityKWh;

    public ElectricCar(int id, String model, String manufacturer, double batteryCapacityKWh) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.batteryCapacityKWh = batteryCapacityKWh;
    }

    //----------------------------------Getters----------------------------------
    public int getId() {
        return id;
    }
    public String getModel() {
        return model;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public ChargingStation getChargingStation() {
        return chargingStation;
    }
    public double getBatteryCapacityKWh() {
        return batteryCapacityKWh;
    }

    //----------------------------------Setters----------------------------------
    public void setId(int id) {
        this.id = id;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }
    public void setBatteryCapacityKWh(double batteryCapacityKWh) {
        this.batteryCapacityKWh = batteryCapacityKWh;
    }
}
