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

        createStages();

        createLeagues();
    }

    private void createLeagues()
    {
        leagues.put("TMGL Stage 1", new League("TMGL Stage 1", Arrays.asList(
                teams.get("ALL"),
                teams.get("BDS"),
                teams.get("BIG"),
                teams.get("G1"),
                teams.get("ITB"),
                teams.get("KC"),
                teams.get("SIN"),
                teams.get("SLY")))
        );
    }

    private void createStages()
    {
        stages.put("Playday 1", new Stage(
                "Playday 1",
                "1",
                mapPacks.get("Pack 1"))
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
    }
}
