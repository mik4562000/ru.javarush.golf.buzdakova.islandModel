package residents;

import island.Location;

import java.util.List;

public class Herbivore extends Animal {
    @Override
    public void eat(Location currentLocation) {
        //eat plant
        List<Plant> plants = currentLocation.getPlants();
        double saturation = 0;
        while (saturation != getFoodSaturationWeight() && !plants.isEmpty()) {
            saturation = saturation + Plant.WEIGHT;
            currentLocation.removePlant();
        }
        // if not satisfied then eat animals
        if (saturation != getFoodSaturationWeight()) {
            super.eat(currentLocation);
        }
    }
}
