public class Ora extends ElectricCar {
    private boolean hasPanaromicSunroof;
    private boolean hasWirelessCharging;

    public Ora(int id, String model, String manufacturer, double batteryCapacityKWh,
               boolean hasPanaromicSunroof, boolean hasWirelessCharging) {
        super(id, model, manufacturer, batteryCapacityKWh);
        this.hasPanaromicSunroof = hasPanaromicSunroof;
        this.hasWirelessCharging = hasWirelessCharging;
    }

    //----------------------------------Getters----------------------------------
    public boolean hasPanaromicSunroof() {
        return hasPanaromicSunroof;
    }
    public boolean hasWirelessCharging() {
        return hasWirelessCharging;
    }

    //----------------------------------Setters----------------------------------
    public void setPanaromicSunroof(boolean panaromicSunroof) {
        hasPanaromicSunroof = panaromicSunroof;
    }
    public void setWirelessCharging(boolean wirelessCharging) {
        hasWirelessCharging = wirelessCharging;
    }
}
