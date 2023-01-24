package main.data.types;

import lombok.Data;

@Data
public class Team
{
    private String name;
    private String shortName;
    private Player player1;
    private Player player2;
    private String castLink;
    private String castLanguage;

    public String getName()
    {
        return name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public boolean equals(Team team)
    {
        return team.name.equals(this.name);
    }
}
