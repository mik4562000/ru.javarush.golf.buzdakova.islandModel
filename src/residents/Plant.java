package residents;

import island.Location;

public class Plant {
    private static final int WEIGHT = 1;
    private static final int MAX_NUMBER_PER_LOCATION = 200;

    public void thrive(Location location) {
        location.addPlant();
    }

    public static int getWeight() {
        return WEIGHT;
    }

    public static int getMaxNumberPerLocation() {
        return MAX_NUMBER_PER_LOCATION;
    }
}
