package main.data.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team
{
    private String name;
    private String shortName;
    private String player1;
    private String player2;
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
