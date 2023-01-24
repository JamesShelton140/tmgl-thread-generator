package main.data.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Maps
{
    AEROPIPES("Aeropipes"),
    AGILITY_DASH("Agility Dash"),
    BACK_N_FORTH("Back'N'Forth"),
    FREESTYLE("Freestyle"),
    GYROSCOPE("Gyroscope"),
    PARKOUR("Parkour"),
    REPS("Reps"),
    SLIPPYSLIDES("SlippySlides"),
    SLOWDOWN("Slowdown");

    private String name;

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
