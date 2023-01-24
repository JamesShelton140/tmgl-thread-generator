package main.data.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapPack
{
    private String name;
    private List<Maps> maps;
}
