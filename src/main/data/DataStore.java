package main.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import main.data.types.League;
import main.data.types.MapPack;
import main.data.types.Maps;
import main.data.types.Match;
import main.data.types.Stage;
import main.data.types.Team;

@Data
public class DataStore
{
    private Map<String, Team> teams = new HashMap<>();
    private Map<String, MapPack> mapPacks = new HashMap<>();
    private Map<String, Stage> stages = new HashMap<>();
    private Map<String, League> leagues = new HashMap<>();

    private List<Match> matches = new ArrayList<>();

    public DataStore()
    {
        createTeams();

        createMapPacks();

        createLeagues();
    }

    private void createLeagues()
    {
        leagues.put("TMGL Stage 1", new League("Grand League", "TMGL", "https://www.twitch.tv/wirtual",
                Arrays.asList(
                    playday(1),
                    playday(2),
                    playday(3),
                    playday(4),
                    playday(5),
                    playday(6),
                    playday(7)
                ),

                Arrays.asList(
                    teams.get("ALL"),
                    teams.get("BDS"),
                    teams.get("BIG"),
                    teams.get("G1"),
                    teams.get("ITB"),
                    teams.get("KC"),
                    teams.get("SIN"),
                    teams.get("SLY")
                ))
        );

        leagues.put("TMCL Stage 1", new League("Challenger League", "TMCL", "https://www.twitch.tv/trackmania",
                Arrays.asList(
                    playday(1),
                    playday(2),
                    playday(3),
                    playday(4),
                    playday(5),
                    playday(6),
                    playday(7)
                ),

                Arrays.asList(
                    teams.get("ATX"),
                    teams.get("BS+"),
                    teams.get("EXA"),
                    teams.get("HOM"),
                    teams.get("IZI"),
                    teams.get("ORK"),
                    teams.get("SCR"),
                    teams.get("SPR")
                ))
        );
    }

    private Stage playday(int i)
    {
        String mapPack;
        switch (i)
        {
            case 1:
            case 2:
                mapPack = "Pack 1";
                break;
            default:
                mapPack = "Pack 1";
        }

        return new Stage(
                "Playday " + i,
                Integer.toString(i),
                mapPacks.get(mapPack)
        );
    }

    private void createMapPacks()
    {
        mapPacks.put("Pack 1", new MapPack("Pack 1", Arrays.asList(
                Maps.AEROPIPES,
                Maps.AGILITY_DASH,
                Maps.BACK_N_FORTH,
                Maps.FLIP_OF_FAITH,
                Maps.FREESTYLE,
                Maps.GYROSCOPE,
                Maps.PARKOUR,
                Maps.REPS,
                Maps.SLIPPYSLIDES,
                Maps.SLOWDOWN))
        );
    }

    private void createTeams()
    {
        teams.put("ALL", new Team(
                "Alliance",
                "ALL",
                "Mudda",
                "Soulja",
                "https://www.twitch.tv/spammiej",
                "en")
        );

        teams.put("BDS", new Team(
                "BDS Esport",
                "BDS",
                "Affi",
                "Aurel",
                "https://www.twitch.tv/teambds",
                "fr")
        );

        teams.put("BIG", new Team(
                "BIG",
                "BIG",
                "Massa",
                "GranaDy",
                "https://www.twitch.tv/trilluxe",
                "de")
        );

        teams.put("G1", new Team(
                "Gamers First",
                "G1",
                "Gwen",
                "Binks",
                "https://www.twitch.tv/g1live",
                "en")
        );

        teams.put("ITB", new Team(
                "Into The Breach",
                "ITB",
                "mime",
                "eLconn21",
                "https://www.twitch.tv/majijej",
                "en")
        );

        teams.put("KC", new Team(
                "Karmine Corp",
                "KC",
                "Bren",
                "Otaaaq",
                "https://www.twitch.tv/kamet0",
                "fr")
        );

        teams.put("SIN", new Team(
                "SINNERS",
                "SIN",
                "Kappa",
                "tween",
                "https://www.twitch.tv/marty_vole",
                "cs")
        );

        teams.put("SLY", new Team(
                "Solary",
                "SLY",
                "Carl Jr.",
                "Pac",
                "https://www.twitch.tv/solary",
                "fr")
        );

        teams.put("ATX", new Team(
                "ALTERNATE aTTaX",
                "ATX",
                "Wosile",
                "Skandear",
                "",
                "")
        );

        teams.put("BS+", new Team(
                "BS+COMPETITION",
                "BS+",
                "Snow",
                "Glast",
                "https://www.twitch.tv/bscompetition",
                "en")
        );

        teams.put("EXA", new Team(
                "Exalty",
                "EXA",
                "link",
                "MiQuatro",
                "https://www.twitch.tv/luckersturbo",
                "en")
        );

        teams.put("HOM", new Team(
                "Homyno Tsun",
                "HOM",
                "Feed",
                "Ener",
                "https://www.twitch.tv/tmggeek",
                "fr")
        );

        teams.put("IZI", new Team(
                "IziDream",
                "IZI",
                "Cocow",
                "Worker",
                "",
                "")
        );

        teams.put("ORK", new Team(
                "orKs GP Numelops",
                "ORK",
                "Complex",
                "Panda",
                "https://www.twitch.tv/orkstv",
                "fr")
        );

        teams.put("SCR", new Team(
                "Schweineaim Racing",
                "SCR",
                "Ratchet",
                "Barbos",
                "",
                "")
        );

        teams.put("SPR", new Team(
                "Sprout",
                "SPR",
                "Scrapie",
                "Dexter",
                "",
                "")
        );
    }
}
