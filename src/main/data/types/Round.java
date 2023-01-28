package main.data.types;

import java.util.HashMap;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Round
{
    private final Team blueTeam;
    private final Team redTeam;
    private HashMap<String, Integer> result;

    public int getScore(Team team)
    {
        if(!result.containsKey(team.getPlayer1()) || !result.containsKey(team.getPlayer2())) return 0;

        int teamResult = result.get(team.getPlayer1()) + result.get(team.getPlayer2());
        int score;
        switch (teamResult)
        {
            case 3:
                score = 3;
                break;
            case 4:
                score = 2;
                break;
            case 5:
            case 6:
                score = 1;
                break;
            default:
                score = 0;
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
