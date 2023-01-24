package main.data.types;

import java.util.HashMap;
import lombok.Data;

@Data
public class MapPick
{
    private Maps map;
    private Team blueTeam;
    private Team redTeam;
    private String pickTeam = null;
    private Team winner = null;
    private HashMap<Integer, Round> rounds;

    public int getScore(Team team)
    {
        determineWinner();
        return winner != null && team.equals(winner) ?  1 : 0;
    }

    public void determineWinner()
    {
        int blueScore = 0;
        int redScore = 0;

        for (Round round : rounds.values())
        {
            blueScore += round.getBlueScore();
            redScore += round.getRedScore();
        }

        if (blueScore > redScore) winner = blueTeam;
        if (blueScore < redScore) winner = redTeam;
    }
}
