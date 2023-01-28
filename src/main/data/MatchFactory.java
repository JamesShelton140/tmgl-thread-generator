package main.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JTextArea;
import main.data.types.MapPick;
import main.data.types.Maps;
import main.data.types.Match;
import main.data.types.Round;
import main.data.types.Stage;
import main.data.types.Team;
import main.panel.MainMenu;
import main.panel.components.ButtonScrollList;
import main.panel.components.RoundResultPanel;

public class MatchFactory
{
    private final DataStore data;
    private final MainMenu menu;

    public MatchFactory(DataStore data, MainMenu menu)
    {

        this.data = data;
        this.menu = menu;
    }

    public void newMatch()
    {
        Match match = new Match();

        getLeague(match);

//        match.setStage(getStage(match));
//
//        List<Team> teams = getTeams(match);
//        match.setBlueTeam(teams.get(0));
//        match.setRedTeam(teams.get(1));
//
//        match.setFirstBan(getFirstBan(match));
//        List<Maps> bans = getBans(match);
//        if(match.getFirstBan().equals("blue"))
//        {
//            match.setBlueTeamBan(bans.get(0));
//            match.setRedTeamBan(bans.get(1));
//        }
//        else
//        {
//            match.setBlueTeamBan(bans.get(1));
//            match.setRedTeamBan(bans.get(0));
//        }
//
//        match.setMapPicks(getPicks(match));
//
//        match.setSkippedMap(getSkippedMap(match));
//


//        return match;
    }

    private boolean hasBeenPicked(Match match, Maps map)
    {
        return map.equals(match.getBlueTeamBan())
                || map.equals(match.getRedTeamBan())
                || match.getMapPicks().values().stream()
                .map(MapPick::getMap)
                .anyMatch(map::equals);
    }

    private void displayThread(Match match)
    {
        MatchMarkdownSerializer serializer = new MatchMarkdownSerializer();

        String thread = serializer.serialize(match);

        menu.showComponent(new JTextArea(thread));
    }

    private void playRound(Match match, MapPick mapPick, int mapNumber)
    {
        int roundNumber = mapPick.getRounds().size() + 1;
        Round round = new Round(mapPick.getBlueTeam(), mapPick.getRedTeam());
        mapPick.addRound(round);

        menu.showComponent(new RoundResultPanel(mapPick.getMap().getName() + " round " + roundNumber, match.getPlayers(), round, e -> {
            if(!mapPick.hasWinner())
            {
                playRound(match, mapPick, mapNumber);
            }
            else
            {
                if(!match.hasWinner())
                {
                    playMap(match, mapNumber + 1);
                }
                else
                {
                    displayThread(match);
                }
            }
        }));
    }

    private void playMap(Match match, int mapNumber)
    {
        MapPick mapPick = match.getMapPick(mapNumber);

        playRound(match, mapPick, mapNumber);
    }

    private void getSkippedMap(Match match)
    {
        match.setSkippedMap(match.getStage().getMapPack().getMaps().stream()
                .filter((map) -> !(hasBeenPicked(match, map)))
                .findFirst().get());
//        System.out.println(match.getMapPicks().values().stream().map(MapPick::getMap).collect(Collectors.toList()));

        playMap(match, 1);
    }

    private void getRandomPick(Match match)
    {
        menu.showComponent(new ButtonScrollList("Select random map pick:", match.getStage().getMapPack().getMaps().stream().filter(map -> !hasBeenPicked(match, map)).map(Maps::getName).collect(Collectors.toList()), (e -> {
            MapPick mapPick = new MapPick();
            mapPick.setBlueTeam(match.getBlueTeam());
            mapPick.setRedTeam(match.getRedTeam());
            mapPick.setMap(Arrays.stream(Maps.values())
                    .filter(map -> map.getName().equals(((JButton)e.getSource()).getText()))
                    .findFirst()
                    .get());

            match.addMapPick(mapPick);

            getSkippedMap(match);

        })));
    }

    private void getPick(Match match, String team)
    {
        menu.showComponent(new ButtonScrollList("Select " + team + " team map pick:", match.getStage().getMapPack().getMaps().stream().filter(map -> !hasBeenPicked(match, map)).map(Maps::getName).collect(Collectors.toList()), (e -> {
            MapPick mapPick = new MapPick();
            mapPick.setBlueTeam(match.getBlueTeam());
            mapPick.setRedTeam(match.getRedTeam());
            mapPick.setPickTeam(team);
            mapPick.setMap(Arrays.stream(Maps.values())
                    .filter(map -> map.getName().equals(((JButton)e.getSource()).getText()))
                    .findFirst()
                    .get());

            match.addMapPick(mapPick);

            if(match.getMapPicks().size() < 6)
            {
                getPick(match, team.equals("blue") ? "red" : "blue");
            }
            else
            {
                getRandomPick(match);
            }
        })));
    }

    private void getBan(Match match, String team)
    {
        menu.showComponent(new ButtonScrollList("Select " + team + " team ban:", match.getStage().getMapPack().getMaps().stream().filter(map -> !hasBeenPicked(match, map)).map(Maps::getName).collect(Collectors.toList()), (e -> {
            switch (team)
            {
                case "blue": match.setBlueTeamBan(Arrays.stream(Maps.values())
                        .filter(map -> map.getName().equals(((JButton)e.getSource()).getText()))
                        .findFirst()
                        .get());
                    break;
                case "red": match.setRedTeamBan(Arrays.stream(Maps.values())
                        .filter(map -> map.getName().equals(((JButton)e.getSource()).getText()))
                        .findFirst()
                        .get());
                    break;
            }
            if(team.equals(match.getFirstBan()))
            {
                getBan(match, team.equals("blue") ? "red" : "blue");
            }
            else
            {
                getPick(match, team);
            }
        })));
    }

    private void getFirstBan(Match match)
    {
        menu.showComponent(new ButtonScrollList("Select first team to ban:", Arrays.asList("blue", "red"), (e -> {
            match.setFirstBan(((JButton)e.getSource()).getText());
            getBan(match, match.getFirstBan());
        })));
    }

    private void getRedTeam(Match match)
    {
        menu.showComponent(new ButtonScrollList("Pick Red Team:", match.getLeague().getTeams().stream().map(Team::getName).filter((name) -> !name.equals(match.getBlueTeam().getName())).collect(Collectors.toList()), (e -> {
            match.setRedTeam(match.getLeague().getTeams().stream()
                    .filter(team -> team.getName().equals(((JButton)e.getSource()).getText()))
                    .findFirst()
                    .get());
            getFirstBan(match);
        })));
    }

    private void getBlueTeam(Match match)
    {
        menu.showComponent(new ButtonScrollList("Pick Blue Team:", match.getLeague().getTeams().stream().map(Team::getName).collect(Collectors.toList()), (e -> {
            match.setBlueTeam(match.getLeague().getTeams().stream()
                    .filter(team -> team.getName().equals(((JButton)e.getSource()).getText()))
                    .findFirst()
                    .get());
            getRedTeam(match);
        })));
    }

    private void getStage(Match match)
    {
        menu.showComponent(new ButtonScrollList("Pick Stage:", match.getLeague().getStages().stream().map(Stage::getName).collect(Collectors.toList()), (e -> {
            match.setStage(match.getLeague().getStages().stream()
                    .filter(stage -> stage.getName().equals(((JButton)e.getSource()).getText()))
                    .findFirst()
                    .get());
            getBlueTeam(match);
        })));
    }

    private void getLeague(Match match)
    {
        menu.showComponent(new ButtonScrollList("Pick League:", new ArrayList<String>(data.getLeagues().keySet()), (e -> {
            match.setLeague(data.getLeagues().get(((JButton)e.getSource()).getText()));
            getStage(match);
        })));
    }
}
