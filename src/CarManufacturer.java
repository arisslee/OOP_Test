import java.util.List;

public class CarManufacturer {
    private String id;
    private String name;
    private List<String> carModels;

    public CarManufacturer(String id, String name, List<String> carModels) {
        this.id = id;
        this.name = name;
        this.carModels = carModels;
    }

    //----------------------------------Getters----------------------------------
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<String> getCarModels() {
        return carModels;
    }

    //----------------------------------Setters----------------------------------
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCarModels(List<String> carModels) {
        this.carModels = carModels;
    }
}
