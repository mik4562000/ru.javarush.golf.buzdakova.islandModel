package residents;

import island.Coordinates;
import island.Island;
import island.Location;

import java.util.*;

public abstract class Animal {
    public static double WEIGHT;
    public static int MAX_NUMBER_PER_LOCATION;
    public static int MOVEMENT_SPEED;
    public static double FOOD_SATURATION_WEIGHT;
    public static Map<Class<? extends Animal>, Integer> foodProbability;

    private static double getWeight() {
        return WEIGHT;
    }

    public static int getMaxNumberPerLocation() {
        return MAX_NUMBER_PER_LOCATION;
    }

    private static int getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    protected static double getFoodSaturationWeight() {
        return FOOD_SATURATION_WEIGHT;
    }

/*    private double getSumProbability() {
        return foodProbability.values().stream().mapToInt(i -> i).sum();
    }
*/
    public void eat(Location currentLocation) {
        List<Animal> foodList = currentLocation.getAvailableAnimals(this);
        double saturation = 0;
        while (saturation < getFoodSaturationWeight() && !foodList.isEmpty()) {
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

        /*Class<? extends Animal> aClass = this.getClass();
        Class<? extends Animal> aClass1 = victim.getClass();
        boolean isEat = true;
        if (true) {
        }*/
    }

    protected Animal chooseFood(List<Animal> foodList) {
        Random random = new Random();
        int probability = random.nextInt(100) + 1;
        for (var food : foodList) {
            if (probability <= foodProbability.get(food.getClass())) {
                return food;
            }
        }
        return null;
    }

    public void breed(Location currentLocation) {
        currentLocation.babyAnimalBirth(this);
    }

    public void chooseDirection() {

    }

    public void move(Location currentLocation, Island island) {

        //Coordinates coordinates = new Coordinates(currentLocation.coordinates);
        //coordinates.x = changeCoordinate(coordinates.x, deltaX);
        Coordinates coordinates = findNextCoordinates(currentLocation.getCoordinates(), island);

        currentLocation.remove(this);
        island.moveToOtherLocation(coordinates, this);

    }

    private int changeCoordinate(int coordinate, int delta, int boundary) {
        Random random = new Random();
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

    protected Coordinates findNextCoordinates(Coordinates currentCoordinates, Island island) {

        Random random = new Random();
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

    //public abstract void print();


    public void print() {
        System.out.println(WEIGHT);
    }

    @Override
    public String toString() {
        return "Animal{}";
    }
}

