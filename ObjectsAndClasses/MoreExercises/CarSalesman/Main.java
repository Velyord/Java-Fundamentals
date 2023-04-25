package ObjectsAndClasses.MoreExercises.CarSalesman;

import static ObjectsAndClasses.MoreExercises.CarSalesman.NumberValidator.setNumber;
import static ObjectsAndClasses.MoreExercises.CarSalesman.StringValidator.setText;
import static java.lang.System.out;

public class Main {
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
        int m = setNumber();
        cars = new Car[m];

        for (int i = 0; i < m; i++) {
            String[] carInfo = setText().split("\\s+");
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
        int n = setNumber();
        engines = new Engine[n];

        for (int i = 0; i < n; i++) {
            String[] engineInfo = setText().split("\\s+");
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