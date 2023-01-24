package main.panel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import main.data.types.Team;

public class TeamList extends JPanel
{
    private List<Team> teams;

    public TeamList(List<Team> teams)
    {
        this.teams = teams;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for(Team team : teams.stream().sorted(Comparator.comparing(Team::getName)).collect(Collectors.toList()))
        {
            this.add(new TeamPanel(team));
        }
    }
}
