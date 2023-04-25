package ObjectsAndClasses.MoreExercises.TeamworkProjects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ObjectsAndClasses.MoreExercises.TeamworkProjects.NumberValidator.setNumber;
import static ObjectsAndClasses.MoreExercises.TeamworkProjects.StringValidator.setText;
import static java.lang.System.out;

public class Main {
    private static final List<Team> teams = new ArrayList<>();

    public static void main(String[] args) {
        populateTeams();
        addMembersToTeams();
        printTeams();
    }

    private static void printTeams() {
        teams.stream()
            .filter(team -> team.getMembers().size() > 0)
            .sorted((team1, team2) -> {
                int result = Integer.compare(team2.getMembers().size(), team1.getMembers().size());

                if (result == 0) {
                    result = team1.getName().compareTo(team2.getName());
                }

                return result;
            })
            .forEach(team -> {
                out.println(team.getName());
                out.printf("- %s\n", team.getCreatorName());

                team.getMembers().stream()
                    .sorted(String::compareTo)
                    .forEach(member -> out.printf("-- %s\n", member));
            });

        out.println("Teams to disband:");

        teams.stream()
            .filter(team -> team.getMembers().size() == 0)
            .sorted(Comparator.comparing(Team::getName))
            .forEach(team -> out.println(team.getName()));
    }

    private static void addMembersToTeams() {
        String input = setText();

        while (!input.equals("end of assignment")) {
            String[] tokens = input.split("->");
            String memberName = tokens[0];
            String teamName = tokens[1];

            if (doesTeamExist(teamName)) {
                if (isAMember(memberName)) {
                    out.printf("Member %s cannot join team %s!\n", memberName, teamName);
                } else {
                    addMemberToTeam(memberName, teamName);
                }
            } else {
                out.printf("Team %s does not exist!\n", teamName);
            }

            input = setText();
        }
    }

    private static boolean isAMember(String memberName) {
        for (Team team : teams) {
            if (team.getCreatorName().equals(memberName)) {
                return true;
            }

            for (String member : team.getMembers()) {
                if (member.equals(memberName)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void addMemberToTeam(String memberName, String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                team.addMember(memberName);
            }
        }
    }

    private static void populateTeams() {
        int teamsCount = setNumber();

        for (int i = 0; i < teamsCount; i++) {
            String[] userInfo = setText().split("-");
            String creatorName = userInfo[0];
            String teamName = userInfo[1];

            if (isACreator(creatorName)) {
                out.printf("%s cannot create another team!\n", creatorName);
            } else {
                if (doesTeamExist(teamName)) {
                    out.printf("Team %s was already created!\n", teamName);
                } else {
                    teams.add(new Team(teamName, creatorName));
                    out.printf("Team %s has been created by %s!\n", teamName, creatorName);
                }
            }
        }
    }

    private static boolean isACreator(String creatorName) {
        for (Team team : teams) {
            if (team.getCreatorName().equals(creatorName)) {
                return true;
            }
        }

        return false;
    }

    private static boolean doesTeamExist(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return true;
            }
        }

        return false;
    }
}