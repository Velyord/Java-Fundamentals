package ObjectsAndClasses.MoreExercises.RawData;

import static ObjectsAndClasses.MoreExercises.RawData.NumberValidator.setNumber;
import static ObjectsAndClasses.MoreExercises.RawData.StringValidator.setText;
import static ObjectsAndClasses.MoreExercises.RawData.PersonalUtils.*;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        int n = setNumber();
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

        String command = setText();

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
