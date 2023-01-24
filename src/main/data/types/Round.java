package main.data.types;

import java.util.HashMap;
import lombok.Data;

@Data
public class Round
{
    private Team blueTeam;
    private Team redTeam;
    private HashMap<String, Integer> result;

    public int getScore(Team team)
    {
        int teamResult = result.get(team.getPlayer1()) + result.get(team.getPlayer2());
        int score = switch (teamResult)
        {
            case 3 -> 3;
            case 4 -> 2;
            case 5, 6 -> 1;
            default -> 0;
        };

        return score;
    }

    public int getBlueScore()
    {
        return getScore(blueTeam);
    }

    public int getRedScore()
    {
        return getScore(redTeam);
    }
}
