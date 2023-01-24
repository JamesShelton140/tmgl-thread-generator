package main.data.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stage
{
    private String name; //Playday 1
    private String shortName;
    private MapPack mapPack;

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "Playday " + name;
    }
}
