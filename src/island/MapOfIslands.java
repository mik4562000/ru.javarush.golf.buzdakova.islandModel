package island;

import java.util.*;

public class MapOfIslands {

    private static final Map<Island, Location[][]> islands = new HashMap<>();

    public static void addIsland(Island island) {
        islands.put(island, island.locations);
    }

    public static Island getIslandByLocation(Location location) {
        for (Island island : islands.keySet()) {
            Location[] locations = islands.values().stream().flatMap(arr -> Arrays.stream(arr).flatMap(Arrays::stream)).toArray(Location[]::new);
            if (Arrays.asList(locations).contains(location)) {
                return island;
            }
        }
        return null;
    }

}
