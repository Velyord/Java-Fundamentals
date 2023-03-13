package ObjectsAndClasses.Exercise.groomingSalon;
// package groomingSalon // for judge

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private int capacity;
    private List<Pet> data = new ArrayList<>();

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void add(Pet pet) {
        if (data.size() < capacity) {
            data.add(pet);
        }
    }

    public boolean remove(String petName) {
        return data.removeIf(pet -> pet.getName().equals(petName));
    }

    public Pet getPet(String petName, String ownerName) {
        for (Pet pet : data) {
            if (pet.getName().equals(petName) &&
                pet.getOwner().equals(ownerName)) {
                return pet;
            }
        }
        return null;
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        output.append("The grooming salon has the following clients:\n");
        for (Pet pet : data) {
            output.append(pet.getName()).append(" ").append(pet.getOwner()).append("\n");
        }
        return output.toString();
    }
}
