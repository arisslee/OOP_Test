import java.util.*;

public class EVCarManagementSystemTest {
    private static Map<String, Customer> customers = new HashMap<>();
    private static List<ChargingStation> chargingStations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create electric car manufacturers
        CarManufacturer teslaManufacturer = new CarManufacturer("M1", "Tesla",
                Arrays.asList("Model S", "Model 3", "Model X", "Model Y"));
        CarManufacturer oraManufacturer = new CarManufacturer("M2", "Ora",
                Arrays.asList("Ora E2", "Ora E6", "Ora E8"));

        // Create charging stations
        ChargingStation cs1 = new ChargingStation("CS1", "Gelang Patah", 100);
        ChargingStation cs2 = new ChargingStation("CS2", "Skudai", 150);
        ChargingStation cs3 = new ChargingStation("CS3", "Pontian", 120);
        chargingStations.add(cs1);
        chargingStations.add(cs2);
        chargingStations.add(cs3);

        // Display electric car manufacturers and their car models in a nice table form
        System.out.println("                  【 EV Car Manufacturer and Model Lists 】                 ");
        System.out.println("+------------------+--------------------+----------------+----------------+");
        System.out.println("| Manufacturer ID  | Manufacturer Name  | Car Model ID   | Car Model Name |");
        System.out.println("+------------------+--------------------+----------------+----------------+");
        displayCarManufacturerInfo(teslaManufacturer, "T");
        System.out.println("+------------------+--------------------+----------------+----------------+");
        displayCarManufacturerInfo(oraManufacturer, "O");
        System.out.println("+------------------+--------------------+----------------+----------------+");

        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please select an option:");
            System.out.println("1. Purchase Electric Cars");
            System.out.println("2. View / Update Charging Station");
            System.out.println("3. Display EV Car Manufacturer and Model Lists");
            System.out.println("4. Exit");

            // Get user choice
            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice (1, 2, 3, or 4): ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid input. Please enter a valid choice (1, 2, 3, or 4).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid choice (1, 2, 3, or 4).");
                    scanner.next(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    purchaseElectricCars();
                    break;
                case 2:
                    // Ask for customer ID to view and update charging station
                    System.out.print("Enter your Customer ID: ");
                    String customerId = scanner.next();
                    Customer customer = customers.get(customerId);
                    if (customer != null) {
                        viewAndUpdateChargingStation(customer);
                    } else {
                        System.out.println("Invalid Customer ID. Please enter a valid ID.");
                    }
                    break;
                case 3:
                    // Display EV Car Manufacturer and Model Lists
                    System.out.println("                  【 EV Car Manufacturer and Model Lists 】                 ");
                    System.out.println("+------------------+--------------------+----------------+----------------+");
                    System.out.println("| Manufacturer ID  | Manufacturer Name  | Car Model ID   | Car Model Name |");
                    System.out.println("+------------------+--------------------+----------------+----------------+");
                    displayCarManufacturerInfo(teslaManufacturer, "T");
                    System.out.println("+------------------+--------------------+----------------+----------------+");
                    displayCarManufacturerInfo(oraManufacturer, "O");
                    System.out.println("+------------------+--------------------+----------------+----------------+");
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    exitProgram = true;
                    break;
            }
        }
        scanner.close();
    }

    public static void purchaseElectricCars() {
        // Ask customer's name
        System.out.print("\nEnter your name: ");
        String name = scanner.next();

        // Generate customerID
        String customerId = "C" + (customers.size() + 1);

        // Create a new customer
        Customer customer = new Customer(customerId, name);
        customers.put(customerId, customer);

        // Display welcome message with customer ID
        System.out.println("\nWelcome, " + name + "! Your customer ID is: " + customerId);

        // Ask for car model IDs to purchase
        List<String> purchasedCarModels = new ArrayList<>();
        boolean continuePurchase = true;
        while (continuePurchase) {
            System.out.print("Enter the Car Model ID you want to purchase (or '0' to finish): ");
            String carModelId = scanner.next();

            if (carModelId.equals("0")) {
                continuePurchase = false;
            } else {
                try {
                    // Validate and add car model to customer's list of owned cars
                    boolean validCarModel = validateAndAddCarModel(customer, carModelId);
                    if (validCarModel) {
                        System.out.println("Purchase successfully!");
                        purchasedCarModels.add(carModelId);
                    } else {
                        System.out.println("Invalid Car Model ID. Please enter a valid ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid Car Model ID.");
                }
            }
        }

        // Display the list of cars owned by the customer
        System.out.println("\nElectric Cars Owned by " + name + ":");
        System.out.println("+------------------+--------------------+--------------------+");
        System.out.println("| Car Model ID     |  Manufacturer Name |   Car Model Name   |");
        System.out.println("+------------------+--------------------+--------------------+");
        displayOwnedCarModels(customer, purchasedCarModels);
        System.out.println("+------------------+--------------------+--------------------+");

        // Set default charging station (CS1) for each car
        System.out.println("\nNote: Your default charging station is CS1 (Gelang Patah).");
        System.out.println("If you want to change the charging station for each car, you can update it via Option 2.");

        // Create default charging station (CS1) for each purchased car
        ChargingStation defaultChargingStation = findChargingStationById("CS1");
        for (String carModelId : purchasedCarModels) {
            customer.addChargingStation(carModelId, createDefaultChargingStation(defaultChargingStation));
        }
    }

    public static ChargingStation createDefaultChargingStation(ChargingStation defaultChargingStation) {
        // Create a new ChargingStation object with the same properties as the default one
        return new ChargingStation(defaultChargingStation.getStationId(),
                defaultChargingStation.getLocation(),
                defaultChargingStation.getAvailableCapacityKWh());
    }

    public static boolean validateAndAddCarModel(Customer customer, String carModelId) {
        CarManufacturer manufacturer;
        int carModelIndex;

        if (carModelId.startsWith("T") && carModelId.length() > 1) {
            manufacturer = new CarManufacturer("M1", "Tesla",
                    Arrays.asList("Model S", "Model 3", "Model X", "Model Y"));
            try {
                carModelIndex = Integer.parseInt(carModelId.substring(1)) - 1;
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (carModelId.startsWith("O") && carModelId.length() > 1) {
            manufacturer = new CarManufacturer("M2", "Ora",
                    Arrays.asList("Ora E2", "Ora E6", "Ora E8"));
            try {
                carModelIndex = Integer.parseInt(carModelId.substring(1)) - 1;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }

        List<String> carModels = manufacturer.getCarModels();
        if (carModelIndex >= 0 && carModelIndex < carModels.size()) {
            customer.addOwnedCar(carModelId, carModels.get(carModelIndex));
            ChargingStation defaultChargingStation = findChargingStationById("CS1");
            customer.addChargingStation(carModelId, defaultChargingStation);
            return true;
        } else {
            return false;
        }
    }

    public static void displayCarManufacturerInfo(CarManufacturer manufacturer, String prefix) {
        String manufacturerId = manufacturer.getId();
        String manufacturerName = manufacturer.getName();
        List<String> carModels = manufacturer.getCarModels();
        for (int i = 0; i < carModels.size(); i++) {
            String carModelName = carModels.get(i);
            String carModelId = prefix + (i + 1);
            System.out.printf("| %-16s | %-18s | %-14s | %-14s |%n", centerAlign(manufacturerId, 16),
                    centerAlign(manufacturerName, 18), centerAlign(carModelId, 12),
                    centerAlign(carModelName, 14));
        }
    }

    public static void displayOwnedCarModels(Customer customer, List<String> carModelIds) {
        for (String carModelId : carModelIds) {
            CarManufacturer manufacturer = getCarManufacturerFromCarModelId(carModelId);
            String carModelName = customer.getOwnedCars().get(carModelId);
            String manufacturerName = manufacturer != null ? manufacturer.getName() : "N/A";
            System.out.printf("| %-16s | %-18s | %-18s |%n", centerAlign(carModelId, 16),
                    centerAlign(manufacturerName, 18), centerAlign(carModelName, 18));
        }
    }

    public static void viewAndUpdateChargingStation(Customer customer) {
        System.out.println("\nAvailable Charging Stations:");
        System.out.println("+-----------------+-----------------+-------------------------+");
        System.out.println("|   Station ID    |     Location    |    Available Capacity   |");
        System.out.println("+-----------------+-----------------+-------------------------+");
        for (ChargingStation station : chargingStations) {
            System.out.printf("| %-15s | %-15s | %-19.2f kWh |%n",
                    centerAlign(station.getStationId(), 15),
                    centerAlign(station.getLocation(), 15),
                    station.getAvailableCapacityKWh());
        }
        System.out.println("+-----------------+-----------------+-------------------------+");

        System.out.println("\nCharging Stations for Cars Owned by " + customer.getName() + ":");
        System.out.println("+-----------------+-----------------+-----------------+-----------------+");
        System.out.println("|   Car Model ID  |   Manufacturer  |  Car Model Name |    Station ID   |");
        System.out.println("+-----------------+-----------------+-----------------+-----------------+");

        for (Map.Entry<String, String> entry : customer.getOwnedCars().entrySet()) {
            String carModelId = entry.getKey();
            String carModelName = entry.getValue();
            CarManufacturer manufacturer = getCarManufacturerFromCarModelId(carModelId);
            String manufacturerName = manufacturer != null ? manufacturer.getName() : "N/A";
            ChargingStation chargingStation = customer.getChargingStations().get(carModelId);
            String stationId = chargingStation != null ? chargingStation.getStationId() : "N/A";

            System.out.printf("| %-15s | %-15s | %-15s | %-15s |%n",
                    centerAlign(carModelId, 15),
                    centerAlign(manufacturerName, 15),
                    centerAlign(carModelName, 15),
                    centerAlign(stationId, 15));
        }

        System.out.println("+-----------------+-----------------+-----------------+-----------------+");

        System.out.print("\nDo you want to update the charging station for any car? (Enter 'Y' for Yes, 'N' for No): ");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("Y")) {
            updateChargingStation(customer);
        }
    }

    public static void updateChargingStation(Customer customer) {
        System.out.print("\nEnter the Car Model ID for which you want to update the charging station: ");
        String carModelId = scanner.next();

        ChargingStation chargingStation = customer.getChargingStations().get(carModelId);
        if (chargingStation == null) {
            System.out.println("You do not own a car with the specified Car Model ID.");
        } else {
            System.out.println("\nAvailable Charging Stations:");
            System.out.println("+-----------------+-----------------------+---------------------------+");
            System.out.println("|    Station ID   |        Location       |     Available Capacity    |");
            System.out.println("+-----------------+-----------------------+---------------------------+");
            for (ChargingStation station : chargingStations) {
                System.out.printf("| %-15s | %-19s | %-21.2f kWh |%n",
                        centerAlign(station.getStationId(), 15),
                        centerAlign(station.getLocation(), 21),
                        station.getAvailableCapacityKWh());
            }
            System.out.println("+-----------------+-----------------------+---------------------------+");

            System.out.print("\nEnter the Station ID for the new charging station: ");
            String stationId = scanner.next();
            ChargingStation newChargingStation = findChargingStationById(stationId);
            if (newChargingStation == null) {
                System.out.println("Invalid Station ID. No charging station found with the specified ID.");
            } else {
                chargingStation.setStationId(newChargingStation.getStationId());
                chargingStation.setLocation(newChargingStation.getLocation());
                chargingStation.setAvailableCapacityKWh(newChargingStation.getAvailableCapacityKWh());
                System.out.println("Charging station update successfully for Car Model ID " + carModelId);
            }
        }
    }

    public static ChargingStation findChargingStationById(String stationId) {
        for (ChargingStation station : chargingStations) {
            if (station.getStationId().equals(stationId)) {
                return station;
            }
        }
        return null;
    }

    public static CarManufacturer getCarManufacturerFromCarModelId(String carModelId) {
        if (carModelId.startsWith("T") && carModelId.length() > 1) {
            return new CarManufacturer("M1", "Tesla",
                    Arrays.asList("Model S", "Model 3", "Model X", "Model Y"));
        } else if (carModelId.startsWith("O") && carModelId.length() > 1) {
            return new CarManufacturer("M2", "Ora",
                    Arrays.asList("Ora E2", "Ora E6", "Ora E8"));
        } else {
            return null;
        }
    }

    public static String centerAlign(Object value, int width) {
        String stringValue = String.valueOf(value);
        int padding = width - stringValue.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return String.format("%" + leftPadding + "s%s%" + rightPadding + "s", "", stringValue, "");
    }
}