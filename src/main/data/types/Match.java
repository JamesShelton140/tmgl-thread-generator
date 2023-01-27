package main.data.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

@Data
public class Match
{
    private League league;
    private Stage stage;
    private Team blueTeam;
    private Team redTeam;
    private MapPack mapPack;
    private String firstBan;
    private Maps blueTeamBan;
    private Maps redTeamBan;
    private HashMap<Integer, MapPick> mapPicks = new HashMap<>();
    private Maps skippedMap;

    public void addMapPick(MapPick mapPick)
    {
        mapPicks.put(mapPicks.size() + 1, mapPick);
    }

    public int getScore(Team team)
    {
        int score = 0;
        for (MapPick mapPick : mapPicks.values())
        {
            if(mapPick.hasWinner() && mapPick.getWinner().equals(team))
                score++;
        }
        return score;
    }

    public int getScore(String colour)
    {
        Team team = null;

        switch (colour)
        {
            case "blue" -> team = blueTeam;
            case "red" -> team = redTeam;
        }

        return getScore(team);
    }

    public int getBlueScore()
    {
        return getScore("blue");
    }

    public int getRedScore()
    {
        return getScore("red");
    }

    public MapPick getMapPick(int i)
    {
        return mapPicks.get(i);
    }

    public Maps getBan(String colour)
    {
        Maps map = null;

        switch (colour)
        {
            case "blue" -> map = blueTeamBan;
            case "red" -> map = redTeamBan;
        }

        return map;
    }

    public boolean hasWinner()
    {
        return getBlueScore() == 4 || getRedScore() == 4;
    }

    public List<String> getPlayers()
    {
        return Arrays.asList(blueTeam.getPlayer1(), blueTeam.getPlayer2(), redTeam.getPlayer1(), redTeam.getPlayer2());
    }
}
