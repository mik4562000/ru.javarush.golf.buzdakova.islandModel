package island;

import residents.Animal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        try {
            ExecutorService executorService = Executors.newWorkStealingPool(4);
            CountDownLatch countDownLatch = new CountDownLatch(locations.length * locations[0].length);
            for (Location[] locationRow : locations) {
                for (Location location : locationRow) {
                    executorService.execute(() -> {
                        location.prosper();
                        countDownLatch.countDown();
                    });
                }
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void prepareLocationsForNewDay() {
        for (Location[] locationRow : locations) {
            for (Location location : locationRow) {
                location.prepareAnimalListForNewDay();
            }
        }
    }
}

