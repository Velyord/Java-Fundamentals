/*
Task:
    You are the owner of a courier company and want to make a system for tracking your cars and their cargo.
    Define a class Car that holds information about the model, engine, cargo, and a collection of exactly 4 tires.
    The engine, cargo, and tire should be separate classes,
    and create a constructor that receives all information about the Car and creates and initializes its inner
    components (engine, cargo, and tires).
    On the first line of input, you will receive a number N -
    the number of cars you have, on each of the next N lines you
    will receive information about a car in the format
    "{Model} {EngineSpeed} {EnginePower} {CargoWeight} {CargoType} {Tire1Pressure}
    {Tire1Age} {Tire2Pressure} {Tire2Age} {Tire3Pressure} {Tire3Age} {Tire4Pressure}
    {Tire4Age}", where the speed, power, weight and tire age are integers, tire pressure is a double.
    After the N lines, you will receive a single line with one of 2 commands "fragile" or "flamable",
    if the command is "fragile" print all cars whose Cargo Type is "fragile"
    with a tire whose pressure is < 1 if the command is "flamable" print all cars whose Cargo Type is "flamable"
    and have Engine Power > 250. The cars should be printed in order to appear in the input.
    © SoftUni – about.softuni.bg. Copyrighted document. Unauthorized copy, reproduction or use is not permitted.
Follow us:
    Page 2 of 4
Examples:
    2
    ChevroletAstro 200 180 1000 fragile 1.3 1 1.5 2 1.4 2 1.7 4
    Citroen2CV 190 165 1200 fragile 0.9 3 0.85 2 0.95 2 1.1 1
    fragile
    ->
    Citroen2CV

    4
    ChevroletExpress 215 255 1200 flamable 2.5 1 2.4 2 2.7 1 2.8 1
    ChevroletAstro 210 230 1000 flamable 2 1 1.9 2 1.7 3 2.1 1
    DaciaDokker 230 275 1400 flamable 2.2 1 2.3 1 2.4 1 2 1
    Citroen2CV 190 165 1200 fragile 0.8 3 0.85 2 0.7 5 0.95 2
    flamable
    ->
    ChevroletExpress
    DaciaDokker
 */
package ObjectsAndClasses.MoreExercises;

import java.util.Scanner;

import static java.lang.System.out;

public class RawData {
    private static final Scanner scanner = new Scanner(System.in);

    private static class Engine {
        private final int speed;
        private final int power;

        public Engine(int speed, int power) {
            this.speed = speed;
            this.power = power;
        }

        public int getSpeed() {
            return speed;
        }

        public int getPower() {
            return power;
        }
    }

    private static class Cargo {
        private final int weight;
        private final String type;

        public Cargo(int weight, String type) {
            this.weight = weight;
            this.type = type;
        }

        public int getWeight() {
            return weight;
        }

        public String getType() {
            return type;
        }
    }

    private static class Tire {
        private final double pressure;
        private final int age;

        public Tire(double pressure, int age) {
            this.pressure = pressure;
            this.age = age;
        }

        public double getPressure() {
            return pressure;
        }

        public int getAge() {
            return age;
        }
    }

    private static class Car {
        private final String model;
        private final Engine engine;
        private final Cargo cargo;
        private final Tire[] tires;

        public Car(String model, Engine engine, Cargo cargo, Tire[] tires) {
            this.model = model;
            this.engine = engine;
            this.cargo = cargo;
            this.tires = tires;
        }

        public String getModel() {
            return model;
        }

        public Engine getEngine() {
            return engine;
        }

        public Cargo getCargo() {
            return cargo;
        }

        public Tire[] getTires() {
            return tires;
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());
        Car[] cars = new Car[n];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String model = input[0];
            int engineSpeed = Integer.parseInt(input[1]);
            int enginePower = Integer.parseInt(input[2]);
            int cargoWeight = Integer.parseInt(input[3]);
            String cargoType = input[4];
            double tire1Pressure = Double.parseDouble(input[5]);
            int tire1Age = Integer.parseInt(input[6]);
            double tire2Pressure = Double.parseDouble(input[7]);
            int tire2Age = Integer.parseInt(input[8]);
            double tire3Pressure = Double.parseDouble(input[9]);
            int tire3Age = Integer.parseInt(input[10]);
            double tire4Pressure = Double.parseDouble(input[11]);
            int tire4Age = Integer.parseInt(input[12]);
            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Tire[] tires = new Tire[4];
            tires[0] = new Tire(tire1Pressure, tire1Age);
            tires[1] = new Tire(tire2Pressure, tire2Age);
            tires[2] = new Tire(tire3Pressure, tire3Age);
            tires[3] = new Tire(tire4Pressure, tire4Age);
            cars[i] = new Car(model, engine, cargo, tires);
        }

        String command = scanner.nextLine();

        if (command.equals("fragile")) {
            for (Car car : cars) {
                if (car.getCargo().getType().equals("fragile")) {
                    for (Tire tire : car.getTires()) {
                        if (tire.getPressure() < 1) {
                            out.println(car.getModel());
                            break;
                        }
                    }
                }
            }
        } else if (command.equals("flamable")) {
            for (Car car : cars) {
                if (car.getCargo().getType().equals("flamable")) {
                    if (car.getEngine().getPower() > 250) {
                        out.println(car.getModel());
                    }
                }
            }
        }
    }
}
