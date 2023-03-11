/*
Task:
    You have to make a catalog for vehicles. You will receive two
    types of vehicles - a car or a truck.
    Until you receive the command "End" you will receive lines of
    input in the format:
    "{typeOfVehicle} {model} {color} {horsepower}"
    After the "End" command, you will start receiving models of
    vehicles. Print for every received vehicle its data in the
    format:
    Type: {typeOfVehicle}
    Model: {modelOfVehicle}
    Color: {colorOfVehicle}
    Horsepower: {horsepowerOfVehicle}

    When you receive the command "Close the Catalogue", stop
    receiving input and print the average horsepower for the cars
    and the trucks in the format:
    "{typeOfVehicles} have average horsepower of
    {averageHorsepower}."
    The average horsepower is calculated by dividing the sum of
    horsepower for all vehicles of the type by the total count of
    vehicles from the same type.
    Format the answer to the 2nd decimal point.
Constraints:
    •	The type of vehicle will always be a car or truck.
    •	You will not receive the same model twice.
    •	The received horsepower will be an integer in the interval
    [1…1000].
    •	You will receive at most 50 vehicles.
    •	Single whitespace will be used for the separator.
Examples:
    truck Man red 200
    truck Mercedes blue 300
    car Ford green 120
    car Ferrari red 550
    car Lamborghini orange 570
    End
    Ferrari
    Ford
    Man
    Close the Catalogue
    ->
    Type: Car
    Model: Ferrari
    Color: red
    Horsepower: 550
    Type: Car
    Model: Ford
    Color: green
    Horsepower: 120
    Type: Truck
    Model: Man
    Color: red
    Horsepower: 200
    Cars have average horsepower of: 413.33.
    Trucks have average horsepower of: 250.00.

    car Opel green 736
    End
    Close the Catalogue
    ->
    Cars have average horsepower of: 736.00.
    Trucks have average horsepower of: 0.00.
*/
package ObjectsAndClasses.Exercise.VehicleCatalogue;

import java.util.ArrayList;
import java.util.List;

import static ObjectsAndClasses.Exercise.VehicleCatalogue.StringValidator.setText;
import static java.lang.System.out;

public class Main {
    private static final List<Vehicle> vehicleList = new ArrayList<>();

    public static void main(String[] args) {
        String input = setText();

        while (!input.equals("End")) {
            addToCatalogue(input);
            input = setText();
        }

        input = setText();

        while (!input.equals("Close the Catalogue")) {
            printModelInfo(input);
            input = setText();
        }

        double avgCarHorsepower =
                getAverageHorsepower("car");
        double avgTruckHorsepower =
                getAverageHorsepower("truck");

        printAverageHorsepower(avgCarHorsepower,"Cars");
        printAverageHorsepower(avgTruckHorsepower,"Trucks");
    }

    private static void printAverageHorsepower(
            double avgHorsepower,
            String label
    ) {
        out.printf(
                "%s have average horsepower of: %.2f.\n",
                label, avgHorsepower
        );
    }

    private static void printModelInfo(String input) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getModel().equals(input)) {
                out.printf(
                        "Type: %s\nModel: %s\nColor: %s\nHorsepower: %d\n",
                        vehicle.getType().substring(0, 1).toUpperCase() + vehicle.getType().substring(1),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getHorsepower()
                );
            }
        }
    }

    private static void addToCatalogue(String input) {
        String[] vehicleInfo = input.split(" ");
        String type = vehicleInfo[0];
        String model = vehicleInfo[1];
        String color = vehicleInfo[2];
        int horsepower = Integer.parseInt(vehicleInfo[3]);
        Vehicle vehicle =
                new Vehicle(type, model, color, horsepower);

        vehicleList.add(vehicle);
    }

    private static double getAverageHorsepower(String type) {
        int sumHorsepower = 0;
        int count = 0;

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getType().equals(type)) {
                sumHorsepower += vehicle.getHorsepower();
                count += 1;
            }
        }

        double avgHorsepower = (double) sumHorsepower / count;

        if (Double.isNaN(avgHorsepower)) {
            avgHorsepower = 0;
        }

        return avgHorsepower;
    }
}