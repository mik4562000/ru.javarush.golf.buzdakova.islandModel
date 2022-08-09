package island;

import org.jetbrains.annotations.NotNull;
import residents.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Location {

    List<Class<? extends Animal>> animalsPopulation = List.of(
            Bear.class,
            Boa.class,
            Boar.class,
            Buffalo.class,
            Caterpillar.class,
            Deer.class,
            Duck.class,
            Eagle.class,
            Fox.class,
            Goat.class,
            Horse.class,
            Mouse.class,
            Rabbit.class,
            Sheep.class,
            Wolf.class
    );

    private final Coordinates coordinates;
    private final List<Plant> plants;
    private final List<Animal> animals;
    private final Random random = new Random();

    public Location(int x, int y) {
        coordinates = new Coordinates(x, y);
        this.plants = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return " | " + coordinates.getX() + " , " + coordinates.getY() + " animals = " + animals + " | ";
    }

    public void prosper() {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            animal.eat(this);
            animal.breed(this);
            Island islandByLocation = MapOfIslands.getIslandByLocation(this);
            if (islandByLocation != null) {
                animal.move(this, islandByLocation);
            }
        }
        for (int i = 0; i < plants.size() / 2; i++) {
            boolean isSucceed = addPlant();
            if (!isSucceed) break;
        }
    }

    public void remove(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant() {
        plants.remove(0);
    }

    public void add(Animal animal) {
        long sameAnimalCount = animals.stream().filter(a -> a.getClass() == animal.getClass()).count();
        if (sameAnimalCount < animal.getMaxNumberPerLocation()) {
            animals.add(animal);
        }
    }

    public boolean addPlant() {
        if (plants.size() < Plant.getMaxNumberPerLocation()) {
            plants.add(new Plant());
            return true;
        }
        return false;
    }

    public void babyAnimalBirth(Animal animal) {
        long sameAnimalsCount = animals.stream().filter(a -> a.getClass() == animal.getClass()).count();
        Object babyAnimal = null;
        if (sameAnimalsCount > 1) {
            try {
                Constructor<?> constructor = animal.getClass().getConstructor();
                babyAnimal = constructor.newInstance();
            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (babyAnimal != null) {
            add((Animal) babyAnimal);
        }
    }

    public void generatePopulation() {

        for (var clazz : animalsPopulation) {
            Constructor<?> constructor;
            try {
                constructor = clazz.getConstructor();
                Animal newAnimal = (Animal) constructor.newInstance();
                animals.add(newAnimal);
                int numberPerLocation = random.nextInt(newAnimal.getMaxNumberPerLocation());
                for (int i = 0; i < numberPerLocation; i++) {
                    animals.add((Animal) constructor.newInstance());
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAvailableAnimals(@NotNull Animal animal) {
        if (animal.getFoodProbability() == null) return null;
        List<Animal> foodList = new ArrayList<>();
        for (Animal food : animals) {
            if (animal.getFoodProbability().containsKey(food.getClass())) {
                foodList.add(food);
            }
        }
        return foodList.isEmpty() ? null : foodList;
    }


}
