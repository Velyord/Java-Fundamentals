/*
Task:
    Peter is a pro MOBA player. He is struggling to become a master of the Challenger tier.
    So he watches the statistics in the tier carefully.
    You will receive several input lines in one of the following formats:
    "{player} -> {position} -> {skill}"
    "{player} vs {player}"
    The player and position are strings. The given skill will be an integer number.
    You need to keep track of every player.
    When you receive a player and his position and skill, add him to the player pool.
    If he isn't present, else add his position and skill or update his skill,
    only if the current position skill is lower than the new value.
    If you receive "{player} vs {player}" and both players exist in the tier,
    they duel with the following rules:
    Compare their positions, if they got at least one in common,
    the player with better total skill points wins, and the other is demoted from the tier -> remove him.
    If they have the same total skill points, the duel is a tie, and they both continue in the Season.
    If they don't have positions in common, the duel isn't happening, and both continue in the Season.
    You should end your program when you receive the command "Season end".
    At that point, you should print the players, ordered by total skill in descending order,
    then ordered by player name in ascending order. For each player print,
    their position and skill are ordered descending by skill and then by position name in ascending order.
Input / Constraints:
    • The input comes in the form of commands in one of the formats specified above.
    • Player and position will always be one-word string, containing no whitespaces.
    • Skill will be an integer in the range [0, 1000].
    • There will be no invalid input lines.
    • The program ends when you receive the command "Season end".
Output:
    • The output format for each player is:
    "{player}: {totalSkill} skill
    - {position} <::> {skill}"
Examples:
    Peter -> Adc -> 400
    George -> Jungle -> 300
    Sam -> Mid -> 200
    Sam -> Support -> 250
    Season end
    ->
    Sam: 450 skill
    - Support <::> 250
    - Mid <::> 200
    Peter: 400 skill
    - Adc <::> 400
    George: 300 skill
    - Jungle <::> 300
        We order the players by total skill points descending, then by name.
        We print every position along its skill ordered descending by skill, then by position name.
    Peter -> Adc -> 400
    Bush -> Tank -> 150
    Faker -> Mid -> 200
    Faker -> Support -> 250
    Faker -> Tank -> 250
    Peter vs Faker
    Faker vs Bush
    Faker vs Hide
    Season end
    ->
    Faker: 700 skill
    - Support <::> 250
    - Tank <::> 250
    - Mid <::> 200
    Peter: 400 skill
    - Adc <::> 400
        Faker and Peter don't have a common position, so the duel isn't valid.
        Faker wins vs Bush /common position: "Tank". Bush is demoted.
        Hide doesn't exist, so the duel isn't valid.
        We print every player left in the tier.
 */
package AssociativeArraysLambdaStreamAPI.MoreExercises;

import java.util.*;

import static java.lang.System.out;

public class MOBAChallenger {
    static Scanner scanner = new Scanner(System.in);

    private static class Player {
        private final String name;
        private final Map<String, Integer> positionsSkillMap = new LinkedHashMap<>();

        public Player(String name, int skill, String position) {
            this.name = name;
            this.positionsSkillMap.put(position, skill);
        }

        public String getName() {
            return name;
        }

        public Map<String, Integer> getPositionsSkillMap() {
            return positionsSkillMap;
        }

        public void addPosition(String position, int skill) {
            if (this.positionsSkillMap.containsKey(position)) {
                if (this.positionsSkillMap.get(position) < skill) {
                    this.positionsSkillMap.put(position, skill);
                }
            } else {
                this.positionsSkillMap.put(position, skill);
            }
        }

        public int getTotalSkill() {
            return this.positionsSkillMap
                    .values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        public boolean hasCommonPosition(Player player) {
            for (String position : this.positionsSkillMap.keySet()) {
                if (player.positionsSkillMap.containsKey(position)) {
                    return true;
                }
            }

            return false;
        }

        public void removePlayer() {
            playersList.remove(this);
        }

        public void duel(Player player) {
            if (this.getTotalSkill() > player.getTotalSkill()) {
                player.removePlayer();
            } else if (this.getTotalSkill() < player.getTotalSkill()) {
                this.removePlayer();
            }
        }
    }

    private static final List<Player> playersList = new ArrayList<>();

    public static void main(String[] args) {
        populatePlayersList();
        printPlayers();
    }

    private static void printPlayers() {
        playersList
            .stream()
            .sorted((player1, player2) -> {
                int result = Integer.compare(player2.getTotalSkill(), player1.getTotalSkill());

                if (result == 0) {
                    result = player1.getName().compareTo(player2.getName());
                }

                return result;
            })
            .forEach(player -> {
                out.printf("%s: %d skill\n", player.getName(), player.getTotalSkill());

                player
                    .getPositionsSkillMap()
                    .entrySet()
                    .stream()
                    .sorted((position1, position2) -> {
                        int result = Integer.compare(position2.getValue(), position1.getValue());

                        if (result == 0) {
                            result = position1.getKey().compareTo(position2.getKey());
                        }

                        return result;
                    })
                    .forEach(position ->
                        out.printf(
                            "- %s <::> %d\n",
                            position.getKey(), position.getValue()
                        )
                    );
            });
    }

    private static void populatePlayersList() {
        String input = scanner.nextLine();

        while (!input.equals("Season end")) {
            String[] tokens = input.split(" -> ");

            if (tokens.length > 1) {
                String playerName = tokens[0];
                String position = tokens[1];
                int skill = Integer.parseInt(tokens[2]);
                boolean playerExists = false;
                Player newPlayer = new Player(playerName, skill, position);

                for (Player player : playersList) {
                    if (player.getName().equals(playerName)) {
                        player.addPosition(position, skill);
                        playerExists = true;
                        break;
                    }
                }

                if (!playerExists) {
                    playersList.add(newPlayer);
                }
            } else {
                String[] duel = input.split(" vs ");
                String player1 = duel[0];
                String player2 = duel[1];
                Player playerOne = new Player(player1, 0, "");
                Player playerTwo = new Player(player2, 0, "");

                for (Player player : playersList) {
                    if (player.getName().equals(player1)) {
                        playerOne = player;
                    } else if (player.getName().equals(player2)) {
                        playerTwo = player;
                    }
                }

                if (playersList.contains(playerOne) && playersList.contains(playerTwo)) {
                    playerOne = playersList.get(playersList.indexOf(playerOne));
                    playerTwo = playersList.get(playersList.indexOf(playerTwo));

                    if (playerOne.hasCommonPosition(playerTwo)) {
                        playerOne.duel(playerTwo);
                    }
                }
            }

            input = scanner.nextLine();
        }
    }
}
