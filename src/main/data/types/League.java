package main.data.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class League
{
    private String name;
    private String shortName;
    private String globalCast;
    private List<Stage> stages;
    private List<Team> teams;
}
