package residents;

import island.Coordinates;
import island.Island;
import island.Location;

import java.util.*;

public abstract class Animal {
    protected double WEIGHT;
    protected int MAX_NUMBER_PER_LOCATION;
    protected int MOVEMENT_SPEED;
    protected double FOOD_SATURATION_WEIGHT;

    protected Map<Class<? extends Animal>, Integer> foodProbability;
    private Random random = new Random();

    private double getWeight() {
        return WEIGHT;
    }

    public int getMaxNumberPerLocation() {
        return MAX_NUMBER_PER_LOCATION;
    }

    private int getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    protected double getFoodSaturationWeight() {
        return FOOD_SATURATION_WEIGHT;
    }

    public Map<Class<? extends Animal>, Integer> getFoodProbability() {
        return foodProbability;
    }

    public void eat(Location currentLocation) {
        List<Animal> foodList = currentLocation.getAvailableAnimals(this);
        double saturation = 0;
        while (saturation < getFoodSaturationWeight() && foodList != null && !foodList.isEmpty()) {
            Animal food = chooseFood(foodList);
            if (food != null) {
                saturation = saturation + food.getWeight();
                currentLocation.remove(food);
                foodList.remove(food);
            }
        }

        if (saturation == 0) {
            currentLocation.remove(this);
        }
    }

    private Animal chooseFood(List<Animal> foodList) {
        int probability = random.nextInt(100) + 1;
        for (var food : foodList) {
            if (probability <= getFoodProbability().get(food.getClass())) {
                return food;
            }
        }
        return null;
    }

    public void breed(Location currentLocation) {
        currentLocation.babyAnimalBirth(this);
    }

    public void move(Location currentLocation, Island island) {
        Coordinates coordinates = findNextCoordinates(currentLocation.getCoordinates(), island);
        currentLocation.remove(this);
        island.moveToOtherLocation(coordinates, this);
    }

    private int changeCoordinate(int coordinate, int delta, int boundary) {
        int chooseDirection = random.nextInt(100) + 1;
        if (chooseDirection < 51) {
            coordinate = coordinate - delta;
            if (coordinate < 0) {
                //return secondDelta - coordinate;
                coordinate = 0;
            }
        } else {
            coordinate = coordinate + delta;
            if (coordinate >= boundary) {
                //return secondDelta + coordinate - boundary + 1;
                coordinate = boundary - 1;
            }
        }
        return coordinate;
    }

    private Coordinates findNextCoordinates(Coordinates currentCoordinates, Island island) {
        int numberOfSteps = random.nextInt(getMovementSpeed() + 1);
        if (numberOfSteps == 0) {
            return currentCoordinates;
        }
        int deltaX = random.nextInt(numberOfSteps + 1);
        int deltaY = numberOfSteps - deltaX;

        int x = changeCoordinate(currentCoordinates.getX(), deltaX, island.locations.length);
        int y = changeCoordinate(currentCoordinates.getY(), deltaY, island.locations[0].length);

        return new Coordinates(x, y);
    }

    @Override
    public String toString() {
        return "Animal{}";
    }
}

