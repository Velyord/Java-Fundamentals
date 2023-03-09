/*
Task:
    Write a program that generates random fake advertisement messages to extol some product.
    The messages must consist of 4 parts: laudatory phrase + event + author + city.
    Use the following predefined parts:
    • Phrases
        ◦ "Excellent product."
        ◦ "Such a great product."
        ◦ "I always use that product."
        ◦ "Best product of its category."
        ◦ "Exceptional product."
        ◦ "I can’t live without this product."
    • Events
        ◦ "Now I feel good."
        ◦ "I have succeeded with this product."
        ◦ "Makes miracles. I am happy of the results!"
        ◦ "I cannot believe but now I feel awesome."
        ◦ "Try it yourself, I am very satisfied."
        ◦ "I feel great!"
    • Authors
        ◦ "Diana"
        ◦ "Petya"
        ◦ "Stella"
        ◦ "Elena"
        ◦ "Katya"
        ◦ "Iva"
        ◦ "Annie"
        ◦ "Eva"
    • Cities
        ◦ "Burgas"
        ◦ "Sofia"
        ◦ "Plovdiv"
        ◦ "Varna"
        ◦ "Ruse"
    The format of the output message is: "{phrase} {event} {author} – {city}".
    As an input, you take the number of messages to be generated.
    Print each random message on a separate line.
Examples:
    3
    ->
    Such a great product. Now I feel good. Elena – Ruse
    Excelent product. Makes miracles. I am happy of the results! Katya – Varna
    Best product of its category. That makes miracles. Eva – Sofia

    4
    ->
    I always use that product. Makes miracles. I am happy of the results! Iva - Ruse
    I can’t live without this product. I cannot believe but now I feel awesome. Katya - Burgas
    Such a great product. Try it yourself, I am very satisfied. Iva - Varna
    Best product of its category. I cannot believe but now I feel awesome. Eva - Ruse
*/
package ObjectsAndClasses.Exercise.AdvertisementMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.lang.System.out;
import static ObjectsAndClasses.Exercise.AdvertisementMessage.NumberValidator.setValue;

public class Main {
    static List<String> phrases = new ArrayList<>();
    static List<String> events = new ArrayList<>();
    static List<String> authors = new ArrayList<>();
    static List<String> cities = new ArrayList<>();
    static List<Ad> generatedAdsList = new ArrayList<>();

    public static void main(String[] args) {
        fillPhrasesList();
        fillEventsList();
        fillAuthorsList();
        fillCitiesList();

        int countOfAds = setValue(0, Integer.MAX_VALUE);

        generateAds(countOfAds);
        printGeneratedAdds();
    }

    private static void fillPhrasesList() {
        phrases.add("Excellent product.");
        phrases.add("Such a great product.");
        phrases.add("I always use that product.");
        phrases.add("Best product of its category.");
        phrases.add("Exceptional product.");
        phrases.add("I can’t live without this product.");
    }

    private static void fillEventsList() {
        events.add("Now I feel good.");
        events.add("I have succeeded with this product.");
        events.add("Makes miracles. I am happy of the results!");
        events.add("I cannot believe but now I feel awesome.");
        events.add("Try it yourself, I am very satisfied.");
        events.add("I feel great!");
    }

    private static void fillAuthorsList() {
        authors.add("Diana");
        authors.add("Petya");
        authors.add("Stella");
        authors.add("Elena");
        authors.add("Katya");
        authors.add("Iva");
        authors.add("Annie");
        authors.add("Eva");
    }

    private static void fillCitiesList() {
        cities.add("Burgas");
        cities.add("Sofia");
        cities.add("Plovdiv");
        cities.add("Varna");
        cities.add("Ruse");
    }

    private static void generateAds(int countOfAds) {
        Random rand = new Random();

        for (int i = 1; i <= countOfAds; i++) {
            int randomPhraseIndex = rand.nextInt(phrases.size());
            int randomEventIndex = rand.nextInt(events.size());
            int randomAuthorIndex = rand.nextInt(authors.size());
            int randomCityIndex = rand.nextInt(cities.size());
            String randomPhrase = phrases.get(randomPhraseIndex);
            String randomEvent = events.get(randomEventIndex);
            String randomAuthor = authors.get(randomAuthorIndex);
            String randomCity = cities.get(randomCityIndex);

            Ad ad = new Ad(randomPhrase, randomEvent, randomAuthor, randomCity);
            generatedAdsList.add(ad);
        }
    }

    private static void printGeneratedAdds() {
        for (Ad ad : generatedAdsList) {
            out.printf(
                    "%s %s %s - %s\n",
                    ad.getPhrase(),
                    ad.getEvent(),
                    ad.getAuthor(),
                    ad.getCity()
            );
        }
    }
}
