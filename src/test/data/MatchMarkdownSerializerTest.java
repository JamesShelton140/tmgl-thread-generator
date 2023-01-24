package test.data;

//import org.junit.*;

import main.data.MatchMarkdownSerializer;
import main.data.types.MapPick;
import main.data.types.Maps;
import main.data.types.Match;
import main.data.types.Player;
import main.data.types.Stage;
import main.data.types.Team;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchMarkdownSerializerTest
{

    private MatchMarkdownSerializer serializer;
    private Match match;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        serializer = new MatchMarkdownSerializer();

        Player blue1 = mock(Player.class);
        when(blue1.getName()).thenReturn("CarlJr");
        Player blue2 = mock(Player.class);
        when(blue2.getName()).thenReturn("Pac");

        Team blueTeam = mock(Team.class);
        when(blueTeam.getName()).thenReturn("Solary");
        when(blueTeam.getShortName()).thenReturn("SLY");
        when(blueTeam.getPlayer1()).thenReturn(blue1);
        when(blueTeam.getPlayer2()).thenReturn(blue2);
        when(blueTeam.getCastLink()).thenReturn("https://www.twitch.tv/solary");
        when(blueTeam.getCastLanguage()).thenReturn("fr");

        Player red1 = mock(Player.class);
        when(red1.getName()).thenReturn("Affi");
        Player red2 = mock(Player.class);
        when(red2.getName()).thenReturn("Aurel");

        Team redTeam = mock(Team.class);
        when(redTeam.getName()).thenReturn("BDS Esport");
        when(redTeam.getShortName()).thenReturn("BDS");
        when(redTeam.getPlayer1()).thenReturn(red1);
        when(redTeam.getPlayer2()).thenReturn(red2);
        when(redTeam.getCastLink()).thenReturn("https://www.twitch.tv/teambds");
        when(redTeam.getCastLanguage()).thenReturn("fr");

        Stage stage = mock(Stage.class);
        when(stage.getName()).thenReturn("Playday 2");

        Maps skippedMap = Maps.PARKOUR;

        match = mock(Match.class);
        when(match.getBlueTeam()).thenReturn(blueTeam);
        when(match.getRedTeam()).thenReturn(redTeam);
        when(match.getStage()).thenReturn(stage);
        when(match.getFirstBan()).thenReturn("red");
        when(match.getBlueScore()).thenReturn(2);
        when(match.getRedScore()).thenReturn(4);
        when(match.getBan("blue")).thenReturn(Maps.GYROSCOPE);
        when(match.getBan("red")).thenReturn(Maps.SLOWDOWN);
        MapPick mapPick1 = mockMapPick("AEROPIPES", blueTeam, redTeam, "blue", blueTeam, 11, 6);
        MapPick mapPick2 = mockMapPick("FLIP_OF_FAITH", blueTeam, redTeam, "red", blueTeam, 10, 6);
        MapPick mapPick3 = mockMapPick("AGILITY_DASH", blueTeam, redTeam, "blue", redTeam, 2, 10);
        MapPick mapPick4 = mockMapPick("BACK_N_FORTH", blueTeam, redTeam, "red", redTeam, 7, 12);
        MapPick mapPick5 = mockMapPick("SLIPPYSLIDES", blueTeam, redTeam, "blue", redTeam, 7, 10);
        MapPick mapPick6 = mockMapPick("FREESTYLE", blueTeam, redTeam, "red", redTeam, 7, 11);
        MapPick mapPick7 = mockMapPick("REPS", blueTeam, redTeam, null, null, 0, 0);
        when(match.getMapPick(1)).thenReturn(mapPick1);
        when(match.getMapPick(2)).thenReturn(mapPick2);
        when(match.getMapPick(3)).thenReturn(mapPick3);
        when(match.getMapPick(4)).thenReturn(mapPick4);
        when(match.getMapPick(5)).thenReturn(mapPick5);
        when(match.getMapPick(6)).thenReturn(mapPick6);
        when(match.getMapPick(7)).thenReturn(mapPick7);
        when(match.getSkippedMap()).thenReturn(skippedMap);
    }

    private MapPick mockMapPick(String mapName, Team blueTeam, Team redTeam, String pickTeam, Team winner, int blueScore, int redScore)
    {
        Maps map = Maps.valueOf(mapName);

        MapPick mapPick = mock(MapPick.class);
        when(mapPick.getMap()).thenReturn(map);
        when(mapPick.getPickTeam()).thenReturn(pickTeam);
        when(mapPick.getWinner()).thenReturn(winner);
        when(mapPick.getScore(blueTeam)).thenReturn(blueScore);
        when(mapPick.getScore(redTeam)).thenReturn(redScore);

        return mapPick;
    }

    /**
     * Test method for {@link main.data.MatchMarkdownSerializer#serialize(Match)}.
     */
    @Test
    public void testSerialize()
    {
        String thread = serializer.serialize(match);

//        System.out.println(thread);

        assertEquals("SLY vs BDS | Trackmania World Tour - Grand League - Playday 2 | Post-Match Thread\n" +
                "\n" +
                "# Solary 2-4 BDS Esport\n" +
                "\n" +
                "**Aeropipes:** 11-6\n" +
                "\n" +
                "**Flip of Faith:** 10-6\n" +
                "\n" +
                "**Agility Dash:** 2-10\n" +
                "\n" +
                "**Back'N'Forth:** 7-12\n" +
                "\n" +
                "**SlippySlides:** 7-10\n" +
                "\n" +
                "**Freestyle:** 7-11\n" +
                "\n" +
                "**Reps:** \\-\n" +
                "\n" +
                "&#x200B;\n" +
                "\n" +
                "# Teams:\n" +
                "\n" +
                "**Solary:** CarlJr & Pac\n" +
                "\n" +
                "[Team Cast [fr]](https://www.twitch.tv/solary)\n" +
                "\n" +
                "**BDS Esport:** Affi & Aurel\n" +
                "\n" +
                "[Team Cast [fr]](https://www.twitch.tv/teambds)\n" +
                "\n" +
                "&#x200B;\n" +
                "\n" +
                "# Map Picks:\n" +
                "\n" +
                "|Solary (BLUE)|MAP|BDS Esport (RED)|\n" +
                "|:-|:-|:-|\n" +
                "||Slowdown|**X**|\n" +
                "|**X**|Gyroscope||\n" +
                "|**✔**|Aeropipes||\n" +
                "||Flip of Faith|**✔**|\n" +
                "|**✔**|Agility Dash||\n" +
                "||Back'N'Forth|**✔**|\n" +
                "|**✔**|SlippySlides||\n" +
                "||Freestyle|**✔**|\n" +
                "||Reps||\n" +
                "||~~Parkour~~||\n" +
                "\n" +
                "&#x200B;\n" +
                "\n" +
                "[Event Discussion Thread](https://www.reddit.com/r/TrackMania/)\n" +
                "\n" +
                "[VOD (main cast)](https://www.twitch.tv/wirtual)", thread);
    }
}
