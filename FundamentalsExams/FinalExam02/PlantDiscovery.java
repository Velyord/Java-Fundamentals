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
    private static final Map<String, Integer> plantRarityMap = new LinkedHashMap<>();
    private static final Map<String, List<Integer>> plantRatingMap = new LinkedHashMap<>();
    private static final Map<String, Double> plantAvgRating = new LinkedHashMap<>();
    private static boolean doesPlantExist;

    public static void main(String[] args) {
        populateRarityMap();
        executeCommands();
        populateAvgMap();
        printPlantInfo();
    }

    private static void executeCommands() {
        String commands = scanner.nextLine();

        while (!commands.equals("Exhibition")) {
            String[] commandParts = commands.split(" ");
            String command = commandParts[0];
            String plant = commandParts[1];

            switch (command) {
                case "Rate:":
                    int rating = Integer.parseInt(commandParts[3]);
                    rate(plant, rating);
                    break;
                case "Update:":
                    int rarity = Integer.parseInt(commandParts[3]);
                    update(plant, rarity);
                    break;
                case "Reset:":
                    reset(plant);
                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void reset(String plant) {
        if (plantRarityMap.containsKey(plant)) {
            doesPlantExist = checkIfEntryExists(plant, "plantRatingMap");

            if (doesPlantExist) {
                plantRatingMap.get(plant).removeAll(plantRatingMap.get(plant));
            }
        }
    }

    private static void update(String plant, int rarity) {
        doesPlantExist = checkIfEntryExists(plant, "plantRarityMap");

        if (doesPlantExist) {
            plantRarityMap.put(plant, rarity);
        }
    }

    private static void rate(String plant, int rating) {
        if (plantRarityMap.containsKey(plant)) {
            doesPlantExist = checkIfEntryExists(plant, "plantRatingMap");

            if (doesPlantExist) {
                plantRatingMap.get(plant).add(rating);
            } else {
                List<Integer> plantRatingList = new ArrayList<>();
                plantRatingList.add(rating);
                plantRatingMap.put(plant, plantRatingList);
            }
        }
    }

    private static void populateRarityMap() {
        int iterations = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= iterations; i++) {
            String plantInfo = scanner.nextLine();
            String plant = plantInfo.split("<->")[0];
            int rarity = Integer.parseInt(plantInfo.split("<->")[1]);

            plantRarityMap.put(plant, rarity);
        }
    }

    private static void populateAvgMap() {
        for (Map.Entry<String, List<Integer>> entry : plantRatingMap.entrySet()) {
            plantAvgRating.put(entry.getKey(), getAverageRatingFor(entry));
        }
    }

    private static void printPlantInfo() {
        out.println("Plants for the exhibition:");

        for (Map.Entry<String, Integer> entry : plantRarityMap.entrySet()) {
            double avgRating = plantAvgRating.get(entry.getKey());

            if (Double.isNaN(avgRating)) {
                avgRating = 0;
            }

            out.printf(
                    "- %s; Rarity: %d; Rating: %.2f\n",
                    entry.getKey(), entry.getValue(), avgRating
            );
        }
    }

    private static Double getAverageRatingFor(Map.Entry<String, List<Integer>> entry) {
        int ratingSum = 0;

        for (int rate : entry.getValue()) {
            ratingSum += rate;
        }

        return (double) ratingSum / entry.getValue().size();
    }

    private static boolean checkIfEntryExists(String plant, String map) {
        if (map.equals("plantRarityMap")) {
            for (Map.Entry<String, Integer> entry : plantRarityMap.entrySet()) {
                if (entry.getKey().equals(plant)) {
                    return true;
                }
            }
        } else if (map.equals("plantRatingMap")){
            for (Map.Entry<String, List<Integer>> entry : plantRatingMap.entrySet()) {
                if (entry.getKey().equals(plant)) {
                    return true;
                }
            }
        }

        return false;
    }
}