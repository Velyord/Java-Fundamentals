/*
Task:
    You have just bought the latest and greatest computer game – Need for Seed III.
    Pick your favorite cars and drive them all you want! We know that you can't wait to start playing.
    On the first line of the standard input,
    you will receive an integer n – the number of cars that you can obtain.
    On the next n lines, the cars themselves will follow with their mileage and fuel available,
    separated by "|" in the following format:
    "{car}|{mileage}|{fuel}"
    Then, you will be receiving different commands, each on a new line, separated by " : ",
    until the "Stop" command is given:
    • "Drive : {car} : {distance} : {fuel}":
        ◦ You need to drive the given distance, and you will need the given fuel to do that.
        If the car doesn't have enough fuel, print: "Not enough fuel to make that ride"
        ◦ If the car has the required fuel available in the tank,
        increase its mileage with the given distance, decrease its fuel with the given fuel, and print:
    "{car} driven for {distance} kilometers. {fuel} liters of fuel consumed."
        ◦ You like driving new cars only, so if a car's mileage reaches 100000 km,
        remove it from the collection(s) and print: "Time to sell the {car}!"
    • "Refuel : {car} : {fuel}":
        ◦ Refill the tank of your car.
        ◦ Each tank can hold a maximum of 75 liters of fuel,
        so if the given amount of fuel is more than you can fit in the tank,
        take only what is required to fill it up.
        ◦ Print a message in the following format: "{car} refueled with {fuel} liters"
    • "Revert : {car} : {kilometers}":
        ◦ Decrease the mileage of the given car with the given kilometers
        and print the kilometers you have decreased it with in the following format:
    "{car} mileage decreased by {amount reverted} kilometers"
        ◦ If the mileage becomes less than 10 000km after it is decreased, just set it to 10 000km and
    DO NOT print anything.
    Upon receiving the "Stop" command,
    you need to print all cars in your possession in the following format:
    "{car} -> Mileage: {mileage} kms, Fuel in the tank: {fuel} lt."
Input/Constraints:
    • The mileage and fuel of the cars will be valid, 32-bit integers, and will never be negative.
    • The fuel and distance amounts in the commands will never be negative.
    • The car names in the commands will always be valid cars in your possession.
Output:
    • All the output messages with the appropriate formats are described in the problem description.
Examples:
    3
    Audi A6|38000|62
    Mercedes CLS|11000|35
    Volkswagen Passat CC|45678|5
    Drive : Audi A6 : 543 : 47
    Drive : Mercedes CLS : 94 : 11
    Drive : Volkswagen Passat CC : 69 : 8
    Refuel : Audi A6 : 50
    Revert : Mercedes CLS : 500
    Revert : Audi A6 : 30000
    Stop
    ->
    Audi A6 driven for 543 kilometers. 47 liters of fuel consumed.
    Mercedes CLS driven for 94 kilometers. 11 liters of fuel consumed.
    Not enough fuel to make that ride
    Audi A6 refueled with 50 liters
    Mercedes CLS mileage decreased by 500 kilometers
    Audi A6 -> Mileage: 10000 kms, Fuel in the tank: 65 lt.
    Mercedes CLS -> Mileage: 10594 kms, Fuel in the tank: 24 lt.
    Volkswagen Passat CC -> Mileage: 45678 kms, Fuel in the tank: 5 lt.
        After we receive the cars with their mileage and fuel, we start driving them.
        When we get to "Drive : Volkswagen Passat CC : 69 : 8" command,
        our program calculates that there is not enough fuel, and we print the appropriate message.
        Then we refuel the Audi A6 with 50 l of fuel and Revert the Mercedes with 500 kilometers.
        When we receive the "Revert : Audi A6 : 30000", we set its mileage to 10000 km,
        because if the current mileage of the Audi is 38543 kms and if we subtract 30000 from it,
        we receive 8543 kms, which is less than 10000 kms.
        After all the commands,
        we print our current collection of cars with their current mileage and current fuel.
    4
    Lamborghini Veneno|11111|74
    Bugatti Veyron|12345|67
    Koenigsegg CCXR|67890|12
    Aston Martin Valkryie|99900|50
    Drive : Koenigsegg CCXR : 382 : 82
    Drive : Aston Martin Valkryie : 99 : 23
    Drive : Aston Martin Valkryie : 2 : 1
    Refuel : Lamborghini Veneno : 40
    Revert : Bugatti Veyron : 2000
    Stop
    ->
    Not enough fuel to make that ride
    Aston Martin Valkryie driven for 99 kilometers. 23 liters of fuel consumed.
    Aston Martin Valkryie driven for 2 kilometers. 1 liters of fuel consumed.
    Time to sell the Aston Martin Valkryie!
    Lamborghini Veneno refueled with 1 liters
    Bugatti Veyron mileage decreased by 2000 kilometers
    Lamborghini Veneno -> Mileage: 11111 kms, Fuel in the tank: 75 lt.
    Bugatti Veyron -> Mileage: 10345 kms, Fuel in the tank: 67 lt.
    Koenigsegg CCXR -> Mileage: 67890 kms, Fuel in the tank: 12 lt.
 */
package FundamentalsExams.FinalExamRetake03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class NeedForSpeedIII {
    private static class Car {
        private String name;
        private int mileage;
        private int fuel;

        public Car(String name, int mileage, int fuel) {
            this.name = name;
            this.mileage = mileage;
            this.fuel = fuel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public int getFuel() {
            return fuel;
        }

        public void setFuel(int fuel) {
            this.fuel = fuel;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Car> carList = new ArrayList<>();

    public static void main(String[] args) {
        populateCarList();
        executeCommands();
        printCars();
    }

    private static void executeCommands() {
        String commands = scanner.nextLine();

        while (!commands.equals("Stop")) {
            String[] commandParts = commands.split(" : ");
            String command = commandParts[0];
            String carName = commandParts[1];

            switch (command) {
                case "Drive":
                    int distance = Integer.parseInt(commandParts[2]);
                    int neededFuel = Integer.parseInt(commandParts[3]);
                    drive(carName, distance, neededFuel);
                    break;
                case "Refuel":
                    int fuelToBeAdded = Integer.parseInt(commandParts[2]);
                    refuel(carName, fuelToBeAdded);
                    break;
                case "Revert":
                    int kilometers = Integer.parseInt(commandParts[2]);
                    revert(carName, kilometers);
                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void populateCarList() {
        int numberOfCars = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numberOfCars; i++) {
            String[] carInfo = scanner.nextLine().split("\\|");
            String carName = carInfo[0];
            int carMileage = Integer.parseInt(carInfo[1]);
            int carFuel = Integer.parseInt(carInfo[2]);
            Car car = new Car(carName, carMileage, carFuel);
            carList.add(car);
        }
    }

    private static void printCars() {
        for (Car car : carList) {
            out.printf(
                "%s -> Mileage: %d kms, Fuel in the tank: %d lt.\n",
                car.getName(), car.getMileage(), car.getFuel()
            );
        }
    }

    private static void revert(String carName, int kilometers) {
        for (Car car : carList) {
            if (car.getName().equals(carName)) {
                if (car.getMileage() - kilometers < 10000) {
                    car.setMileage(10000);
                } else {
                    car.setMileage(car.getMileage() - kilometers);
                    out.printf("%s mileage decreased by %s kilometers\n", carName, kilometers);
                }

                break;
            }
        }
    }

    private static void refuel(String carName, int fuelToBeAdded) {
        for (Car car : carList) {
            if (car.getName().equals(carName)) {
                if (car.getFuel() + fuelToBeAdded > 75) {
                    fuelToBeAdded = 75 - car.getFuel();
                }

                car.setFuel(car.getFuel() + fuelToBeAdded);
                out.printf("%s refueled with %d liters\n", carName, fuelToBeAdded);

                break;
            }
        }
    }

    private static void drive(String carName, int distance, int neededFuel) {
        for (Car car : carList) {
            if (car.getName().equals(carName)) {
                if (car.getFuel() < neededFuel) {
                    out.println("Not enough fuel to make that ride");
                } else {
                    car.setMileage(car.getMileage() + distance);
                    car.setFuel(car.getFuel() - neededFuel);

                    out.printf(
                        "%s driven for %s kilometers. %s liters of fuel consumed.\n",
                        carName, distance, neededFuel
                    );

                    if (car.getMileage() >= 100000) {
                        carList.remove(car);
                        out.printf("Time to sell the %s!\n", carName);
                    }

                    break;
                }
            }
        }
    }
}
