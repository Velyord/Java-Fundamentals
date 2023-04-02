/*
Task:
    Anno 1681. The Caribbean.
    The golden age of piracy.
    You are a well-known pirate captain by the name of Jack Daniels.
    Together with your comrades Jim (Beam) and Johnny (Walker), you have been roaming the seas,
    looking for gold and treasure… and the occasional killing, of course.
    Go ahead, target some wealthy settlements and show them the pirate's way!
    Until the "Sail" command is given, you will be receiving:
        •	You and your crew have targeted cities, with their population and gold, separated by "||".
        •	If you receive a city that has already been received, you have to increase the population and gold with the given values.
    After the "Sail" command, you will start receiving lines of text representing events until the "End" command is given.
    Events will be in the following format:
        •	"Plunder=>{town}=>{people}=>{gold}"
        o	You have successfully attacked and plundered the town, killing the given number of people and stealing the respective amount of gold.
        o	For every town you attack print this message: "{town} plundered! {gold} gold stolen, {people} citizens killed."
        o	If any of those two values (population or gold) reaches zero, the town is disbanded.
        	You need to remove it from your collection of targeted cities and print the following message: "{town} has been wiped off the map!"
        o	There will be no case of receiving more people or gold than there is in the city.
        •	"Prosper=>{town}=>{gold}"
        o	There has been dramatic economic growth in the given city, increasing its treasury by the given amount of gold.
        o	The gold amount can be a negative number, so be careful. If a negative amount of gold is given, print: "Gold added cannot be a negative number!" and ignore the command.
        o	If the given gold is a valid amount, increase the town's gold reserves by the respective amount and print the following message:
        "{gold added} gold added to the city treasury. {town} now has {total gold} gold."
Input:
    •	On the first lines, until the "Sail" command, you will be receiving strings representing the cities with their gold and population, separated by "||"
    •	On the following lines, until the "End" command, you will be receiving strings representing the actions described above, separated by "=>"
Output:
    •	After receiving the "End" command, if there are any existing settlements on your list of targets, you need to print all of them, in the following format:
    "Ahoy, Captain! There are {count} wealthy settlements to go to:
    {town1} -> Population: {people} citizens, Gold: {gold} kg
    {town2} -> Population: {people} citizens, Gold: {gold} kg
       …
    {town…n} -> Population: {people} citizens, Gold: {gold} kg"
    •	If there are no settlements left to plunder, print:
    "Ahoy, Captain! All targets have been plundered and destroyed!"
    Constraints
    •	The initial population and gold of the settlements will be valid 32-bit integers, never negative, or exceed the respective limits.
    •	The town names in the events will always be valid towns that should be on your list.
Examples:
    Tortuga||345000||1250
    Santo Domingo||240000||630
    Havana||410000||1100
    Sail
    Plunder=>Tortuga=>75000=>380
    Prosper=>Santo Domingo=>180
    End
    ->
    Tortuga plundered! 380 gold stolen, 75000 citizens killed.
    180 gold added to the city treasury. Santo Domingo now has 810 gold.
    Ahoy, Captain! There are 3 wealthy settlements to go to:
    Tortuga -> Population: 270000 citizens, Gold: 870 kg
    Santo Domingo -> Population: 240000 citizens, Gold: 810 kg
    Havana -> Population: 410000 citizens, Gold: 1100 kg

    Nassau||95000||1000
    San Juan||930000||1250
    Campeche||270000||690
    Port Royal||320000||1000
    Port Royal||100000||2000
    Sail
    Prosper=>Port Royal=>-200
    Plunder=>Nassau=>94000=>750
    Plunder=>Nassau=>1000=>150
    Plunder=>Campeche=>150000=>690
    End
    ->
    Gold added cannot be a negative number!
    Nassau plundered! 750 gold stolen, 94000 citizens killed.
    Nassau plundered! 150 gold stolen, 1000 citizens killed.
    Nassau has been wiped off the map!
    Campeche plundered! 690 gold stolen, 150000 citizens killed.
    Campeche has been wiped off the map!
    Ahoy, Captain! There are 2 wealthy settlements to go to:
    San Juan -> Population: 930000 citizens, Gold: 1250 kg
    Port Royal -> Population: 420000 citizens, Gold: 3000 kg
 */
package FundamentalsExams.FinalExam05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pirates {
    private static final Scanner scanner = new Scanner(System.in);

    private static class City {
        private final String name;
        private int population;
        private int gold;

        public City(String name, int population, int gold) {
            this.name = name;
            this.population = population;
            this.gold = gold;
        }

        public String getName() {
            return name;
        }

        public int getPopulation() {
            return population;
        }

        public int getGold() {
            return gold;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
    }

    private static final List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        populateCities();
        executeCommands();
        printCities();
    }

    private static void printCities() {
        if (cities.isEmpty()) {
            System.out.println(
                "Ahoy, Captain! All targets have been plundered and destroyed!"
            );
        } else {
            System.out.printf(
                "Ahoy, Captain! There are %d wealthy settlements to go to:%n",
                    cities.size()
            );

            for (City city : cities) {
                System.out.printf(
                    "%s -> Population: %d citizens, Gold: %d kg%n",
                    city.getName(), city.getPopulation(), city.getGold()
                );
            }
        }
    }

    private static void executeCommands() {
        String userInput = scanner.nextLine();

        while (!userInput.equals("End")) {
            if (userInput.equals("test")) {
                for (City city : cities) {
                    System.out.printf(
                        "%s -> Population: %d citizens, Gold: %d kg%n",
                        city.getName(), city.getPopulation(), city.getGold()
                    );
                }
            }
            String[] commandParts = userInput.split("=>");
            String command = commandParts[0];
            String town = commandParts[1];

            switch (command) {
                case "Plunder":
                    int people = Integer.parseInt(commandParts[2]);
                    int goldStolen = Integer.parseInt(commandParts[3]);
                    plunder(town, people, goldStolen);
                    break;
                case "Prosper":
                    int goldAdded = Integer.parseInt(commandParts[2]);
                    prosper(town, goldAdded);
                    break;
            }

            userInput = scanner.nextLine();
        }
    }

    private static void prosper(String town, int goldAdded) {
        if (goldAdded < 0) {
            System.out.println(
                "Gold added cannot be a negative number!"
            );
        } else {
            for (City city : cities) {
                if (city.getName().equals(town)) {
                    city.setGold(city.getGold() + goldAdded);

                    System.out.printf(
                        "%d gold added to the city treasury. %s now has %d gold.%n",
                            goldAdded, town, city.getGold()
                    );

                    break;
                }
            }
        }
    }

    private static void plunder(String town, int people, int goldStolen) {
        for (City city : cities) {
            if (city.getName().equals(town)) {
                city.setPopulation(city.getPopulation() - people);
                city.setGold(city.getGold() - goldStolen);

                System.out.printf(
                    "%s plundered! %d gold stolen, %d citizens killed.%n",
                    town, goldStolen, people
                );

                if (city.getPopulation() <= 0 || city.getGold() <= 0) {
                    cities.remove(city);

                    System.out.printf(
                        "%s has been wiped off the map!%n",
                        town
                    );
                }

                break;
            }
        }
    }

    private static void populateCities() {
        String userInput = scanner.nextLine();

        while (!userInput.equals("Sail")) {
            String[] inputParts = userInput.split("\\|\\|");
            String cityName = inputParts[0];
            int population = Integer.parseInt(inputParts[1]);
            int gold = Integer.parseInt(inputParts[2]);

            int cityIndex = cityIndex(cityName);

            if (cityIndex == -1) {
                City city = new City(cityName, population, gold);
                cities.add(city);
            } else {
                City city = cities.get(cityIndex);
                city.setPopulation(city.getPopulation() + population);
                city.setGold(city.getGold() + gold);
            }

            userInput = scanner.nextLine();
        }
    }

    private static int cityIndex(String cityName) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(cityName)) {
                return i;
            }
        }

        return -1;
    }
}
