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
        int score = 0;

        for (Round round : rounds.values())
        {
            score += round.getScore(team);
        }

        return score;
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
