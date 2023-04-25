package ObjectsAndClasses.MoreExercises.TeamworkProjects;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final String creatorName;
    private final List<String> members;

    public Team(String name, String creatorName) {
        this.name = name;
        this.creatorName = creatorName;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String memberName) {
        this.members.add(memberName);
    }
}
