package residents;

import island.Location;

import java.util.List;

public class Herbivore extends Animal {
    @Override
    public void eat(Location currentLocation) {
        List<Plant> plants = currentLocation.getPlants();
        double saturation = 0;
        while (saturation != getFoodSaturationWeight() && !plants.isEmpty()) {
            saturation = saturation + Plant.getWeight();
            currentLocation.removePlant();
        }

        if (saturation != getFoodSaturationWeight()) {
            super.eat(currentLocation);
        }
    }
}
