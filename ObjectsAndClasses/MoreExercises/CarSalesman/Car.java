package ObjectsAndClasses.MoreExercises.CarSalesman;

public class Car {
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
