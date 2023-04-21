/*
Task:
    Snow White loves her dwarfs, but there are so many, and she doesn't know how to order them.
    Does she order them by name? Or by the color of their hat? Or by physics?
    She can't decide, so it's up to you to write a program that does it for her.
    You will be receiving several input lines which contain data about dwarfs in the following format:
    "{dwarfName} <:> {dwarfHatColor} <:> {dwarfPhysics}"
    The dwarfName and the dwarfHatColor are strings. The dwarfPhysics is an integer.
    You must store the dwarfs in your program. There are several rules, though:
    • If 2 dwarfs have the same name but different hat colors,
    they should be considered different dwarfs, and you should store both of them.
    • If 2 dwarfs have the same name and the same hat color, store the one with the higher physics.
    When you receive the command "Once upon a time", the input ends.
    You must order the dwarfs by physics in descending order and then by the total count of dwarfs with
    the same hat color in descending order.
    Then you must print them all.
Input:
    • The input will consist of several input lines containing dwarf data in the format specified above.
    • The input ends when you receive the command "Once upon a time".
Output:
    • As output, you must print the dwarfs ordered as specified above.
    • The output format is: "({hatColor}) {name} <-> {physics}".
Constraints:
    • The dwarfName will be a string that may contain any ASCII
    character except ' ' (space), '<', ':', '>'.
    • The dwarfHatColor will be a string which may contain any ASCII
    character except ' ' (space), '<', ':', '>'.
    • The dwarfPhysics will be an integer in the range [0, 231 - 1].
    • There will be no invalid input lines.
    • If all sorting criteria fail, the order should be by order of input.
    • It allowed working time/memory: 100ms/16MB.
Examples:
    Peter <:> Red <:> 2000
    Todor <:> Blue <:> 1000
    George <:> Green <:> 1000
    Smith <:> Yellow <:> 4500
    Premis <:> Sam <:> 1000
    Once upon a time
    ->
    (Yellow) Smith <-> 4500
    (Red) Peter <-> 2000
    (Blue) Todor <-> 1000
    (Green) George <-> 1000
    (Sam) Premis <-> 1000

    Peter <:> Red <:> 5000
    Peter <:> Blue <:> 10000
    Peter <:> Red <:> 10000
    George <:> Blue <:> 10000
    Once upon a time
    ->
    (Blue) Peter <-> 10000
    (Blue) George <-> 10000
    (Red) Peter <-> 10000
 */
package AssociativeArraysLambdaStreamAPI.MoreExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snowwhite {
    static Scanner scanner = new Scanner(System.in);

    private static class Dwarf {
        private final String name;
        private final String color;
        private final int physics;

        public Dwarf(String name, String color, int physics) {
            this.name = name;
            this.color = color;
            this.physics = physics;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

        public int getPhysics() {
            return physics;
        }
    }

    private static final List<Dwarf> dwarfs = new ArrayList<>();

    public static void main(String[] args) {
        populateDwarfs();
        deleteDwarfsWithSameNameAndColorDependingOnPhysics();
        printSortedDwarfs();
    }

    private static void printSortedDwarfs() {
        dwarfs
                .stream()
                .sorted((d1, d2) -> {
                    if (d1.getPhysics() == d2.getPhysics()) {
                        return Integer.compare(
                                countDwarfsByColor(d2.getColor()),
                                countDwarfsByColor(d1.getColor())
                        );
                    } else {
                        return Integer.compare(d2.getPhysics(), d1.getPhysics());
                    }
                })
                .forEach(d ->
                        System.out.printf(
                                "(%s) %s <-> %d\n",
                                d.getColor(), d.getName(), d.getPhysics()
                        )
                );
    }

    private static void deleteDwarfsWithSameNameAndColorDependingOnPhysics() {
        for (int i = 0; i < dwarfs.size(); i++) {
            Dwarf dwarf = dwarfs.get(i);

            for (int j = i + 1; j < dwarfs.size(); j++) {
                Dwarf dwarf2 = dwarfs.get(j);

                if (dwarf.getName().equals(dwarf2.getName()) &&
                        dwarf.getColor().equals(dwarf2.getColor())) {
                    if (dwarf.getPhysics() < dwarf2.getPhysics()) {
                        dwarfs.remove(dwarf);
                    } else {
                        dwarfs.remove(dwarf2);
                    }
                }
            }
        }
    }

    private static void populateDwarfs() {
        String input = scanner.nextLine();

        while (!input.equals("Once upon a time")) {
            String[] tokens = input.split(" <:> ");
            String dwarfName = tokens[0];
            String dwarfColor = tokens[1];
            int dwarfPhysics = Integer.parseInt(tokens[2]);
            Dwarf newDwarf = new Dwarf(dwarfName, dwarfColor, dwarfPhysics);

            dwarfs.add(newDwarf);

            input = scanner.nextLine();
        }
    }

    private static int countDwarfsByColor(String color) {
        int count = 0;

        for (Dwarf dwarf : dwarfs) {
            if (dwarf.getColor().equals(color)) {
                count++;
            }
        }

        return count;
    }
}
