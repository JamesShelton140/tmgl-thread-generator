package main.data;

import main.data.types.MapPick;
import main.data.types.Match;
import main.data.types.Team;

public class MatchMarkdownSerializer
{
    public String serialize(Match match)
    {
        Team blueTeam = match.getBlueTeam();
        Team redTeam = match.getRedTeam();

        String title = String.format("%s vs %s | Trackmania World Tour - %s - %s | Post-Match Thread",
                blueTeam.getShortName(),
                redTeam.getShortName(),
                match.getLeague().getName(),
                match.getStage().getName()
        );

        StringBuilder threadBody = new StringBuilder();

        threadBody.append(title);
        threadBody.append("\n\n");

        threadBody.append(heading(String.format("%s %d-%d %s",
                blueTeam.getName(),
                match.getBlueScore(),
                match.getRedScore(),
                redTeam.getName()
        )));
        threadBody.append("\n\n");

        MapPick mapPick;

        for(int i = 1; i < 8; i++)
        {
            mapPick = match.getMapPick(i);
            threadBody.append(bold(mapPick.getMap().getName() + ":"));
            threadBody.append(" ");
            threadBody.append(mapPick.getWinner() == null
                    ? "\\-"
                    : mapPick.getScore(blueTeam) + "-" + mapPick.getScore(redTeam));
            threadBody.append("\n\n");
        }

        threadBody.append("&#x200B;");
        threadBody.append("\n\n");

        threadBody.append(heading("Teams:"));
        threadBody.append("\n\n");

        for (Team team : new Team[]{blueTeam, redTeam})
        {
            threadBody.append(bold(team.getName() + ":"));
            threadBody.append(" " + team.getPlayer1() + " & " + team.getPlayer2());
            threadBody.append("\n\n");
            threadBody.append(team.getCastLink() == null || team.getCastLink().equals("")
                    ? "No Team Cast"
                    : link("Team Cast [" + team.getCastLanguage() + "]", team.getCastLink()));
            threadBody.append("\n\n");
        }

        threadBody.append("&#x200B;");
        threadBody.append("\n\n");

        threadBody.append(heading("Map Picks:"));
        threadBody.append("\n\n");

        threadBody.append(table(blueTeam.getName() + " (BLUE)", "MAP", redTeam.getName() + " (RED)"));
        threadBody.append(table(":-", ":-", ":-"));
        String firstPick = match.getFirstBan();
        String secondPick = match.getMapPick(1).getPickTeam();
        threadBody.append(mapPick(match.getBan(firstPick).getName(), firstPick, false));
        threadBody.append(mapPick(match.getBan(secondPick).getName(), secondPick, false));
        for(int i = 1; i < 8; i++)
        {
            mapPick = match.getMapPick(i);
            threadBody.append(mapPick(mapPick.getMap().getName(), mapPick.getPickTeam(), true));
        }
        threadBody.append(mapPick(match.getSkippedMap().getName(), null, false));
        threadBody.append("\n");

        threadBody.append("&#x200B;");
        threadBody.append("\n\n");

        threadBody.append(link("Event Discussion Thread", "https://www.reddit.com/r/TrackMania/"));
        threadBody.append("\n\n");

        threadBody.append(link("VOD (main cast)", "https://www.twitch.tv/wirtual"));

        return threadBody.toString();
    }

    private String heading(String heading)
    {
        return "# " + heading;
    }

    private String bold(String bold)
    {
        return "**" + bold + "**";
    }

    private String link(String linkText, String url)
    {
        return "[" + linkText + "](" + url +")";
    }

    private String table(String left, String middle, String right)
    {
        return "|" + left + "|" + middle + "|" + right + "|" + "\n";
    }

    private String mapPick(String map, String team, boolean pick)
    {
        String left = "";
        String right = "";

        if(team == null) return table(left, pick ? map : "~~" + map + "~~", right);

        String symbol = bold(pick ? "✔" : "X");

        switch(team)
        {
            case "blue" -> left = symbol;
            case "red" -> right = symbol;
        }
        return table(left, map, right);
    }
}


