package island;

import residents.Animal;

public class Island {
    public final Location[][] locations;

    public Island(int height, int width) {
        locations = new Location[width][height];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Location location = new Location(i, j);
                locations[i][j] = location;
                location.generatePopulation();
            }
        }
        MapOfIslands.addIsland(this);
    }

    public void print() {
        for (Location[] locationRow : locations) {
            for (Location location : locationRow) {
                System.out.println(location);
            }
            System.out.println();
        }
    }

    public void moveToOtherLocation(Coordinates coordinates, Animal animal) {
        Location location = getLocationByCoordinates(coordinates);
        location.add(animal);
    }

    private Location getLocationByCoordinates(Coordinates coordinates) {
        return locations[coordinates.getX()][coordinates.getY()];
    }

    public void liveAnotherDay() {
        for (Location[] locationRow : locations) {
            for (Location location : locationRow) {
                location.prosper();
            }
        }
    }
}
