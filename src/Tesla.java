public class Tesla extends ElectricCar {
    private boolean isAutoPilotEnabled;
    private boolean isFullSelfDrivingEnabled;
    private boolean isTeslaTheatreEnabled;

    public Tesla(int id, String model, String manufacturer, double batteryCapacityKWh,
                 boolean isAutoPilotEnabled, boolean isFullSelfDrivingEnabled, boolean isTeslaTheatreEnabled) {
        super(id, model, manufacturer, batteryCapacityKWh);
        this.isAutoPilotEnabled = isAutoPilotEnabled;
        this.isFullSelfDrivingEnabled = isFullSelfDrivingEnabled;
        this.isTeslaTheatreEnabled = isTeslaTheatreEnabled;
    }

    //----------------------------------Getters----------------------------------
    public boolean isAutoPilotEnabled() {
        return isAutoPilotEnabled;
    }
    public boolean isFullSelfDrivingEnabled() {
        return isFullSelfDrivingEnabled;
    }
    public boolean isTeslaTheatreEnabled() {
        return isTeslaTheatreEnabled;
    }

    //----------------------------------Setters----------------------------------
    public void setFullSelfDrivingEnabled(boolean fullSelfDrivingEnabled) {
        isFullSelfDrivingEnabled = fullSelfDrivingEnabled;
    }
    public void setAutoPilotEnabled(boolean autoPilotEnabled) {
        isAutoPilotEnabled = autoPilotEnabled;
    }
    public void setTeslaTheatreEnabled(boolean teslaTheatreEnabled) {
        isTeslaTheatreEnabled = teslaTheatreEnabled;
    }
}
