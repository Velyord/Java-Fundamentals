/*
Task:
    Write the Sentences Generator Code
    Create the Sentence Model
    To create our sentences we are going to need: names, places, verbs, nouns, adverbs and details. The sentence that we will create is based on the following model:
        · One sentence needs [Who from where] [Action] [Detail] to be created.
        o "Who from where" example: [Name + from + Place] ("David from London").
        o "Action" example: [Adverb] + [Verb] + [Noun] ("calmly watched the sunset").
        o "Detail" example: "near the river", "at home", "in the park".
    Add Words for the Sentences
    Let's start by creating arrays with all the words that we are going to use to create a random sentence. Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.
    To declare an array, define its variable type with square brackets, do it as follow:
    Now let's create our first array and call it "names". To fill the array we have to use curly brackets. Inside the brackets, write names, separated by a comma. These are some example names that you can use:
    "Peter", "Michell", "Jane", "Steve"
    Your array should look like this:
    Now we need to create arrays with words for "places", "verbs", "nouns", "adverbs" and "details". Do this by yourself. Here are some words you can use:
        · Places:
        "Sofia", "Plovdiv", "Varna", "Burgas"
        · Verbs:
        "eats", "holds", "sees", "plays with", "brings"
        · Nouns:
        "stones", "cake", "apple", "laptop", "bikes"
        · Adverbs:
        "slowly", "diligently", "warmly", "sadly", "rapidly"
        · Details:
        "near the river", "at home", "in the park"
    Finally, arrays should look like this:
    More information about arrays: https://www.geeksforgeeks.org/array-class-in-java/.
    Create a Method for Getting a Random Word
    Now we are going to create a method. Generally, methods are useful to improve code reusability by reducing code duplication. If we have the same functionality to perform in multiple places, then we can create one method with the required functionality and reuse it wherever it is necessary for the application. In our case, the method will help us choose random words every time.
    To create a method you need the following things:
        · First, our method should have a return type string.
        · Second, we need a name for the method.
        · Third, we should define the parameters that the method will receive.
    Do it as follows:
    Now let's write the method logic. First, we need to create a variable from the type Random – you already know how to do that:
    Now we should use the nextInt() method of the Random class to choose a random index. However, the index should not be greater than the length of the words array, so do it like this:
    Next thing is to create a variable of type string for our random generated word. This word will be on the randomly-generated index from the words array:
    The last thing we should do is to return our random generated word to the method:
    Now our method getRandomWord() is created and ready to use. It looks like this:
    More information about methods: https://www.geeksforgeeks.org/methods-in-java/.
    It's time for the easy part – let’s make the generator work.
    Implement Generator Logic
    First, we should create an endless while loop. You already know how to do this:
    Now we should create variables for all different random words. To do this we will use our method getRandomWord(), which will do all the work for us.
    First, create a variable from the typed String and name it "randomName". Make the variable keep the result from our getRandomWord() method and pass our words array as an argument to the method. Do it as follow:
    Now try to create variables for the other words yourself. They should all pass the necessary arrays and keep the results from the getRandomWord() method. Finally, it should look like this:
    Next thing is to construct our random sentence and print it on the console. Remember the model that we are working on - we need "Who from where", then "Action" and last "Details":
    Now what is left is to write the sentence on the console. Next, write a message to the user to press [Enter] to generate a new sentence and read his input. You know how to do that:
    You can also write a greeting message before the while loop:
 */
package Projects.RandomSentencesGenerator;

import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String[] names = {"John", "Peter", "Mary", "Jane", "Bob", "Alice", "Tom", "Jerry", "Donald", "Mickey",
            "Minnie", "Goofy", "Pluto", "Daisy"};
    private static final String[] places = {"London", "Paris", "New York", "Tokyo", "Moscow", "Berlin", "Rome", "Madrid", "Barcelona",
            "Milan", "Prague", "Vienna", "Warsaw", "Budapest", "Brussels", "Amsterdam", "Dublin", "Stockholm",
            "Oslo", "Helsinki", "Reykjavik", "Copenhagen", "Lisbon", "Athens", "Bucharest", "Belgrade", "Sofia",
            "Ljubljana", "Bratislava", "Zagreb", "Luxembourg", "Monaco", "San Marino", "Vatican City"};
    private static final String[] adverbs = {"quickly", "slowly", "happily", "sadly", "angrily", "calmly", "quietly", "loudly", "easily",
            "hardly", "carefully", "carelessly", "safely", "dang", "dangerously"};
    private static final String[] verbs = {"walks with", "runs with", "jumps over", "swims with", "flies over",
            "drives with", "sleeps with", "eats", "drinks with", "dances with", "sings to", "talks to", "thinks about the",
            "laughs at", "cries at", "smiles at", "frowns at", "screams at", "yells at", "whispers to", "shouts at",
            "speaks to", "reads to", "writes about the", "studies", "works with", "plays with", "watches", "listens",
            "hears", "sees", "feels the", "touches the", "smells the", "tastes the", "knows the", "understands the",
            "remembers the", "forgets the", "believes the", "wishes about", "loves the", "hates the", "likes the",
            "dislikes the", "wants", "needs", "expects", "imagines", "realizes"};
    private static final String[] nouns = {"stones", "cake", "apple", "laptop", "bikes", "cars", "buses", "trains", "planes", "boats",
            "houses", "buildings", "trees", "flowers", "birds", "cats", "dogs", "fish", "snakes", "monkeys",
            "elephants", "penguins", "pigs", "cows", "horses", "sheep", "chickens", "ducks", "rabbits", "bears",
            "tigers", "lions", "sharks", "whales", "dolphins", "snails", "spiders", "ants", "bees", "butterflies",
            "insects", "bats", "frogs", "toads", "snakes", "lizards", "crocodiles", "alligators", "dinosaurs",
            "unicorns", "dragons", "robots", "aliens", "ghosts", "monsters", "zombies", "vampires", "werewolves",
            "fairies", "angels", "demons", "witches", "wizards", "mermaids", "pirates", "a ninja", "a samurai",
            "a knight", "a princess", "a prince", "a queen", "a king", "a soldier", "the police", "a firefighter",
            "a doctor", "a nurse", "a teacher", "a student", "an engineer", "a scientist", "an astronaut", "a pilot",
            "a chef", "an artist", "a painter", "a singer", "a dancer", "an actor", "an actress", "a musician",
            "a comedian", "a writer", "an author", "a poet", "a painter", "a photographer", "a programmer", "a coder",
            "a designer", "an architect", "a farmer", "a fisherman", "a hunter", "a miner"};
    private static final String[] details = {"in the morning", "in the afternoon", "in the evening", "at night", "near the river",
            "at home", "in the park", "in the forest", "in the zoo", "in the city", "in the country",
            "in the mountains", "in the desert", "in the jungle", "in the ocean", "in the sea", "in the sky",
            "in the space", "in the kitchen", "in the bathroom", "in the bedroom", "in the living room",
            "in the garage", "in the garden", "in the yard", "in the basement", "in the attic", "in the office",
            "in the school", "in the university", "in the hospital", "in the library", "in the store",
            "in the restaurant", "in the cafe", "in the bar", "in the club", "in the gym", "in the pool",
            "in the stadium", "in the theater", "in the cinema", "in the concert hall", "in the museum",
            "in the church", "in the mosque", "in the temple", "in the synagogue", "in the circus",
            "in the amusement park"};

    public static void main(String[] args) {
        out.println("Hello, this is your first random-generated sentence:");
        getRandomSentence();
        String userInput = scanner.nextLine();

        while (userInput.equals("")) {
            getRandomSentence();
            userInput = scanner.nextLine();
        }
    }

    private static void getRandomSentence() {
        String randomName = getRandomWord(names) + " ";
        String randomPlace = "from " + getRandomWord(places) + " ";
        String randomAdverb = getRandomWord(adverbs) + " ";
        String randomVerb = getRandomWord(verbs) + " ";
        String randomNoun = getRandomWord(nouns) + " ";
        String randomDetail = getRandomWord(details) + " ";

        while (
            randomVerb.endsWith(" the ") &&
            (randomNoun.startsWith("the ") || randomNoun.startsWith("a ") || randomNoun.startsWith("an "))
        ) {
            randomNoun = getRandomWord(nouns) + " ";
        }

        String randomAction = randomAdverb + randomVerb + randomNoun;
        String randomSentence = randomName + randomPlace + randomAction + randomDetail;

        out.println(randomSentence);
        out.println("Click [Enter] to generate a new one.");
    }

    public static String getRandomWord(String[] words) {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }
}
