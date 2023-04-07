/*
Task:
    You are a pianist, and you like to keep a list of your favorite piano pieces.
    Create a program to help you organize it and add, change, remove pieces from it!
    On the first line of the standard input, you will receive an integer n –
    the number of pieces you will initially have. On the next n lines,
    the pieces themselves will follow with their composer and key,
    separated by "|" in the following format: "{piece}|{composer}|{key}".
    Then, you will be receiving different commands, each on a new line, separated by "|",
    until the "Stop" command is given:
    • "Add|{piece}|{composer}|{key}":
        ◦ You need to add the given piece with the information about it to the other pieces and print:
    "{piece} by {composer} in {key} added to the collection!"
        ◦ If the piece is already in the collection, print:
    "{piece} is already in the collection!"
    • "Remove|{piece}":
        ◦ If the piece is in the collection, remove it and print:
    "Successfully removed {piece}!"
        ◦ Otherwise, print:
    "Invalid operation! {piece} does not exist in the collection."
    • "ChangeKey|{piece}|{new key}":
        ◦ If the piece is in the collection, change its key with the given one and print:
    "Changed the key of {piece} to {new key}!"
        ◦ Otherwise, print:
    "Invalid operation! {piece} does not exist in the collection."
    Upon receiving the "Stop" command, you need to print all pieces in your collection
    in the following format:
    "{Piece} -> Composer: {composer}, Key: {key}"
Input/Constraints:
    • You will receive a single integer at first – the initial number of pieces in the collection
    • For each piece, you will receive a single line of text with information about it.
    • Then you will receive multiple commands in the way described above until the command "Stop".
Output:
    • All the output messages with the appropriate formats are described in the problem description.
Examples:
    3
    Fur Elise|Beethoven|A Minor
    Moonlight Sonata|Beethoven|C# Minor
    Clair de Lune|Debussy|C# Minor
    Add|Sonata No.2|Chopin|B Minor
    Add|Hungarian Rhapsody No.2|Liszt|C# Minor
    Add|Fur Elise|Beethoven|C# Minor
    Remove|Clair de Lune
    ChangeKey|Moonlight Sonata|C# Major
    Stop
    ->
    Sonata No.2 by Chopin in B Minor added to the collection!
    Hungarian Rhapsody No.2 by Liszt in C# Minor added to the collection!
    Fur Elise is already in the collection!
    Successfully removed Clair de Lune!
    Changed the key of Moonlight Sonata to C# Major!
    Fur Elise -> Composer: Beethoven, Key: A Minor
    Moonlight Sonata -> Composer: Beethoven, Key: C# Major
    Sonata No.2 -> Composer: Chopin, Key: B Minor
    Hungarian Rhapsody No.2 -> Composer: Liszt, Key: C# Minor
        After we receive the initial pieces with their info, we start receiving commands.
        The first two commands are to add a piece to the collection,
        and since the pieces are not already added, we manage to add them. The third add command,
        however, attempts to add a piece, which is already in the collection,
        so we print a special message and don't add the piece. After that, we receive the remove command,
        and since the piece is in the collection, we remove it successfully.
        Finally, the last command says to change the key of a piece.
        Since the key is present in the collection, we modify its key.
        We receive the Stop command, print the information about the pieces, and the program ends.
    4
    Eine kleine Nachtmusik|Mozart|G Major
    La Campanella|Liszt|G# Minor
    The Marriage of Figaro|Mozart|G Major
    Hungarian Dance No.5|Brahms|G Minor
    Add|Spring|Vivaldi|E Major
    Remove|The Marriage of Figaro
    Remove|Turkish March
    ChangeKey|Spring|C Major
    Add|Nocturne|Chopin|C# Minor
    Stop
    ->
    Spring by Vivaldi in E Major added to the collection!
    Successfully removed The Marriage of Figaro!
    Invalid operation! Turkish March does not exist in the collection.
    Changed the key of Spring to C Major!
    Nocturne by Chopin in C# Minor added to the collection!
    Eine kleine Nachtmusik -> Composer: Mozart, Key: G Major
    La Campanella -> Composer: Liszt, Key: G# Minor
    Hungarian Dance No.5 -> Composer: Brahms, Key: G Minor
    Spring -> Composer: Vivaldi, Key: C Major
    Nocturne -> Composer: Chopin, Key: C# Minor
 */
package FundamentalsExams.FinalExamRetake01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class ThePianist {
    static Scanner scanner = new Scanner(System.in);
    private static final List<Piece> pieceList = new ArrayList<>();

    private static class Piece {
        private final String piece;
        private final String composer;
        private String key;

        public Piece(String piece, String composer, String key) {
            this.piece = piece;
            this.composer = composer;
            this.key = key;
        }

        public String getPiece() {
            return piece;
        }

        public String getComposer() {
            return composer;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        populatePieceList();
        executeCommands();
        printPieces();
    }

    private static void executeCommands() {
        String commands = scanner.nextLine();

        while (!commands.equals("Stop")) {
            String[] commandParts = commands.split("\\|");
            String command = commandParts[0];

            switch (command) {
                case "Add":
                    String pieceName = commandParts[1];
                    String composer = commandParts[2];
                    String key = commandParts[3];
                    addPiece(pieceName, composer, key);
                    break;
                case "Remove":
                    String pieceToBeRemoved = commandParts[1];
                    removePiece(pieceToBeRemoved);
                    break;
                case "ChangeKey":
                    String pieceToBeChanged = commandParts[1];
                    String newKey = commandParts[2];
                    changeKey(pieceToBeChanged, newKey);
                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void changeKey(String pieceToBeChanged, String newKey) {
        int pieceIndex = getIndexOf(pieceToBeChanged);

        if (pieceIndex != -1) {
            pieceList.get(pieceIndex).setKey(newKey);

            out.printf("Changed the key of %s to %s!\n", pieceToBeChanged, newKey);
        } else {
            out.printf(
                    "Invalid operation! %s does not exist in the collection.\n",
                    pieceToBeChanged
            );
        }
    }

    private static void removePiece(String pieceToBeRemoved) {
        int pieceIndex = getIndexOf(pieceToBeRemoved);

        if (pieceIndex != -1) {
            pieceList.remove(pieceIndex);

            out.printf("Successfully removed %s!\n", pieceToBeRemoved);
        } else {
            out.printf(
                    "Invalid operation! %s does not exist in the collection.\n",
                    pieceToBeRemoved
            );
        }
    }

    private static void addPiece(String pieceName, String composer, String key) {
        int pieceIndex = getIndexOf(pieceName);

        if (pieceIndex != -1) {
            out.printf("%s is already in the collection!\n", pieceName);
        } else {
            Piece newPiece = new Piece(pieceName, composer, key);
            pieceList.add(newPiece);

            out.printf(
                    "%s by %s in %s added to the collection!\n",
                    pieceName, composer, key
            );
        }
    }

    private static void populatePieceList() {
        int countOfPieces = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= countOfPieces; i++) {
            String[] userInput = scanner.nextLine().split("\\|");
            String pieceName = userInput[0];
            String composer = userInput[1];
            String key = userInput[2];
            Piece piece = new Piece(pieceName, composer, key);
            pieceList.add(piece);
        }
    }

    private static int getIndexOf(String pieceName) {
        int index = -1;

        for (Piece piece : pieceList) {
            if (piece.getPiece().equals(pieceName)) {
                index = pieceList.indexOf(piece);
                break;
            }
        }

        return index;
    }

    private static void printPieces() {
        pieceList.forEach(piece ->
                out.printf(
                        "%s -> Composer: %s, Key: %s\n",
                        piece.getPiece(), piece.getComposer(), piece.getKey()
                )
        );
    }
}
