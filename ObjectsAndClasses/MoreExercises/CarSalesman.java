/*
Task:
    Define two classes Car and Engine. A Car has a model, engine, weight, and color.
    An Engine has a model, power,
    displacement, and efficiency.
    A Car's weight and color and its Engine's displacements and efficiency are optional.
    On the first line,
    you will read a number N which will specify how many lines of engines you will receive, on each of
    the next N lines,
    you will receive information about an Engine in the following format "{Model} {Power}
    {Displacement} {Efficiency}".
    After the lines with engines, on the next line, you will receive a number M –
    specifying the number of Cars that will follow, on each of the next M lines,
    information about a Car will follow in the
    following format "{Model} {Engine} {Weight} {Color}",
    where the engine in the format will be the model
    of an existing Engine.
    When creating the object for a Car, you should keep a reference to the real engine in it,
    instead of just the engine's model,
    note that the optional properties might be missing from the formats.
    Your task is to print each car (in the order you received them)
    and its information in the format defined below, if any
    of the optional fields have not been given, print "n/a" in its place instead:
    "{CarModel}:
      {EngineModel}:
       Power: {EnginePower}
       Displacement: {EngineDisplacement}
       Efficiency: {EngineEfficiency}
      Weight: {CarWeight}
      Color: {CarColor}"
Optional:
    Override the classes' ToString() methods to have a reusable way of displaying the objects.
Examples:
    2
    V8-101 220 50
    V4-33 140 28 B
    3
    FordFocus V4-33 1300 Silver
    FordMustang V8-101
    VolkswagenGolf V4-33 Orange
    ->
    FordFocus:
     V4-33:
      Power: 140
      Displacement: 28
      Efficiency: B
     Weight: 1300
     Color: Silver
    FordMustang:
     V8-101:
      Power: 220
      Displacement: 50
      Efficiency: n/a
     Weight: n/a
     Color: n/a
    VolkswagenGolf:
     V4-33:
      Power: 140
      Displacement: 28
      Efficiency: B
     Weight: n/a
     Color: Orange

    4
    DSL-10 280 B
    V7-55 200 35
    DSL-13 305 55 A+
    V7-54 190 30 D
    4
    FordMondeo DSL-13 Purple
    VolkswagenPolo V7-54 1200
    Yellow
    VolkswagenPassat DSL-10 1375
    Blue
    FordFusion DSL-13
    ->
    FordMondeo:
     DSL-13:
      Power: 305
      Displacement: 55
      Efficiency: A+
     Weight: n/a
     Color: Purple
    VolkswagenPolo:
     V7-54:
      Power: 190
      Displacement: 30
      Efficiency: D
     Weight: 1200
     Color: Yellow
    VolkswagenPassat:
     DSL-10:
      Power: 280
      Displacement: n/a
      Efficiency: B
     Weight: 1375
     Color: Blue
    FordFusion:
     DSL-13:
      Power: 305
      Displacement: 55
      Efficiency: A+
     Weight: n/a
     Color: n/a
 */
package ObjectsAndClasses.MoreExercises;

import java.util.Scanner;

import static java.lang.System.out;

public class CarSalesman {
    private static final Scanner scanner = new Scanner(System.in);

    private static class Engine {
        private final String model;
        private final int power;
        private final int displacement;
        private final String efficiency;

        public Engine(String model, int power, int displacement, String efficiency) {
            this.model = model;
            this.power = power;
            this.displacement = displacement;
            this.efficiency = efficiency;
        }

        public String getModel() {
            return model;
        }

        public int getPower() {
            return power;
        }

        public int getDisplacement() {
            return displacement;
        }

        public String getEfficiency() {
            return efficiency;
        }
    }

    private static class Car {
        private final String model;
        private final Engine engine;
        private final int weight;
        private final String color;

        public Car(String model, Engine engine, int weight, String color) {
            this.model = model;
            this.engine = engine;
            this.weight = weight;
            this.color = color;
        }

        public String getModel() {
            return model;
        }

        public Engine getEngine() {
            return engine;
        }

        public int getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }
    }

    private static Engine[] engines = new Engine[0];
    private static Car[] cars = new Car[0];

    public static void main(String[] args) {
        populateEngines();
        populateCars();
        printCarInfo();
    }

    private static void printCarInfo() {
        for (Car car : cars) {
            out.println(car.getModel() + ":");
            out.println(" " + car.getEngine().getModel() + ":");
            out.println("  Power: " + car.getEngine().getPower());
            out.println("  Displacement: " + (car.getEngine().getDisplacement() == 0 ? "n/a" : car.getEngine().getDisplacement()));
            out.println("  Efficiency: " + (car.getEngine().getEfficiency().equals("") ? "n/a" : car.getEngine().getEfficiency()));
            out.println(" Weight: " + (car.getWeight() == 0 ? "n/a" : car.getWeight()));
            out.println(" Color: " + (car.getColor().equals("") ? "n/a" : car.getColor()));
        }
    }

    private static void populateCars() {
        int m = Integer.parseInt(scanner.nextLine());
        cars = new Car[m];

        for (int i = 0; i < m; i++) {
            String[] carInfo = scanner.nextLine().split("\\s+");
            String model = carInfo[0];
            String engineModel = carInfo[1];
            int weight = 0;
            String color = "";

            Engine engine = getEngineFromModel(engineModel);

            if (carInfo.length == 3) {
                try {
                    weight = Integer.parseInt(carInfo[2]);
                } catch (NumberFormatException e) {
                    color = carInfo[2];
                }
            } else if (carInfo.length == 4) {
                weight = Integer.parseInt(carInfo[2]);
                color = carInfo[3];
            }

            cars[i] = new Car(model, engine, weight, color);
        }
    }

    private static Engine getEngineFromModel(String engineModel) {
        Engine engine = null;

        for (Engine e : engines) {
            if (e.getModel().equals(engineModel)) {
                engine = e;
                break;
            }
        }

        return engine;
    }

    private static void populateEngines() {
        int n = Integer.parseInt(scanner.nextLine());
        engines = new Engine[n];

        for (int i = 0; i < n; i++) {
            String[] engineInfo = scanner.nextLine().split("\\s+");
            String model = engineInfo[0];
            int power = Integer.parseInt(engineInfo[1]);
            int displacement = 0;
            String efficiency = "";

            if (engineInfo.length == 3) {
                try {
                    displacement = Integer.parseInt(engineInfo[2]);
                } catch (NumberFormatException e) {
                    efficiency = engineInfo[2];
                }
            } else if (engineInfo.length == 4) {
                displacement = Integer.parseInt(engineInfo[2]);
                efficiency = engineInfo[3];
            }

            engines[i] = new Engine(model, power, displacement, efficiency);
        }
    }
}