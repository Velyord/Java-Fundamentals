/*
Task:
    You have now returned from your world tour.
    On your way, you have discovered some new plants,
    and you want to gather some information about them and create an exhibition to see which plant is
    highest rated.
    On the first line, you will receive a number n.
    On the next n lines,
    you will be given some information about the plants that you have discovered in the format:
    "{plant}<->{rarity}". Store that information because you will need it later.
    If you receive a plant more than once, update its rarity.
    After that, until you receive the command "Exhibition", you will be given some of these commands:
    • "Rate: {plant} - {rating}" – add the given rating to the plant (store all ratings)
    • "Update: {plant} - {new_rarity}" – update the rarity of the plant with the new one
    • "Reset: {plant}" – remove all the ratings of the given plant
    Note: If any given plant name is invalid, print "error"
    After the command "Exhibition",
    print the information that you have about the plants in the following format:
    "Plants for the exhibition:
    - {plant_name1}; Rarity: {rarity}; Rating: {average_rating}
    - {plant_name2}; Rarity: {rarity}; Rating: {average_rating}
    …
    - {plant_nameN}; Rarity: {rarity}; Rating: {average_rating}"
    The average rating should be formatted to the second decimal place.
Input / Constraints:
    • You will receive the input as described above
    • JavaScript: you will receive a list of strings
Output:
    • Print the information about all plants as described above
Examples:
    3
    Arnoldii<->4
    Woodii<->7
    Welwitschia<->2
    Rate: Woodii - 10
    Rate: Welwitschia - 7
    Rate: Arnoldii - 3
    Rate: Woodii - 5
    Update: Woodii - 5
    Reset: Arnoldii
    Exhibition
    ->
    Plants for the exhibition:
    - Arnoldii; Rarity: 4; Rating: 0.00
    - Woodii; Rarity: 5; Rating: 7.50
    - Welwitschia; Rarity: 2; Rating: 7.00

    2
    Candelabra<->10
    Oahu<->10
    Rate: Oahu - 7
    Rate: Candelabra - 6
    Exhibition
    ->
    Plants for the exhibition:
    - Candelabra; Rarity: 10; Rating: 6.00
    - Oahu; Rarity: 10; Rating: 7.00
 */
package FundamentalsExams.FinalExam02;

import java.util.*;

import static java.lang.System.out;

public class PlantDiscovery {
    private static final Scanner scanner = new Scanner(System.in);

    private static class Plant {
        String name;
        int rarity;
        List<Integer> ratings;

        public Plant(String name, Integer rarity) {
            this.name = name;
            this.rarity = rarity;
            ratings = new ArrayList<>();
        }

        public Double getAvgRating() {
            double avgRating;
            int sum = 0;

            if (ratings.size() > 0) {
                for (int rating : ratings) {
                    sum += rating;
                }

                avgRating = (double) sum / ratings.size();
            } else {
                avgRating = 0.0;
            }

            return avgRating;
        }
    }

    private static final List<Plant> plantList = new ArrayList<>();

    public static void main(String[] args) {
        populatePlantList();
        executeCommands();
        printPlantInfo();
    }

    private static void executeCommands() {
        String commands = scanner.nextLine();

        while (!commands.equals("Exhibition")) {
            String[] commandParts = commands.split(" ");
            String command = commandParts[0];
            String plantName = commandParts[1];

            switch (command) {
                case "Rate:":
                    int rating = Integer.parseInt(commandParts[3]);
                    rate(plantName, rating);
                    break;
                case "Update:":
                    int rarity = Integer.parseInt(commandParts[3]);
                    update(plantName, rarity);
                    break;
                case "Reset:":
                    reset(plantName);
                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void reset(String plantName) {
        int plantIndex = getIndex(plantName);

        if (plantIndex != -1) {
            List<Integer> toBeRemoved = plantList.get(plantIndex).ratings;
            plantList.get(plantIndex).ratings.removeAll(toBeRemoved);
        } else {
            out.println("error");
        }
    }

    private static void update(String plantName, int rarity) {
        int plantIndex = getIndex(plantName);

        if (plantIndex != -1) {
            plantList.get(plantIndex).rarity = rarity;
        } else {
            out.println("error");
        }
    }

    private static void rate(String plantName, int rating) {
        int plantIndex = getIndex(plantName);

        if (plantIndex != -1) {
            plantList.get(plantIndex).ratings.add(rating);
        } else {
            out.println("error");
        }
    }

    private static void populatePlantList() {
        int iterations = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= iterations; i++) {
            String plantInfo = scanner.nextLine();
            String plantName = plantInfo.split("<->")[0];
            int rarity = Integer.parseInt(plantInfo.split("<->")[1]);
            int plantIndex = getIndex(plantName);

            if (plantIndex != -1) {
                plantList.get(plantIndex).rarity = rarity;
            } else {
                Plant plant = new Plant(plantName, rarity);
                plantList.add(plant);
            }
        }
    }

    private static void printPlantInfo() {
        out.println("Plants for the exhibition:");

        for (Plant plant : plantList) {
            double avgRating = plant.getAvgRating();

            out.printf(
                    "- %s; Rarity: %d; Rating: %.2f\n",
                    plant.name, plant.rarity, avgRating
            );
        }
    }

    private static int getIndex(String plantName) {
        for (int index = 0; index < plantList.size(); index++) {
            if (plantList.get(index).name.equals(plantName)) {
                return index;
            }
        }

        return -1;
    }
}