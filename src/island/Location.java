package island;

import org.jetbrains.annotations.NotNull;
import residents.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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
        return " | " + coordinates.getX() +
                " , " + coordinates.getY() +
                " animals = " + printAnimals() +
                " plants = " + plants.size() + " | ";
    }

    private String printAnimals() {

        Map<Class<? extends Animal>, Long> grouped =
                animals.stream().
                        collect(Collectors.groupingBy((o -> o.getClass()), Collectors.counting()));

        StringBuilder builder = new StringBuilder();
        for (Class<? extends Animal> animalClass : grouped.keySet()) {
            try {
                Object o = animalClass.getConstructor().newInstance();
                builder.append(" " + o.toString() + " = " + grouped.get(animalClass));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
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
        int plantsAmount = random.nextInt(Plant.getMaxNumberPerLocation());
        for (int i = 0; i < plantsAmount; i++) {
            boolean isSucceed = addPlant();
            if (!isSucceed) break;
        }
    }

    public synchronized void remove(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant() {
        plants.remove(0);
    }

    public synchronized void add(Animal animal) {
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
        long sameAnimalsCount = 0;
        synchronized (this) {
            sameAnimalsCount = animals.stream().filter(a -> a.getClass() == animal.getClass()).count();
        }
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

        int plantsAmount = random.nextInt(Plant.getMaxNumberPerLocation());
        for (int i = 0; i < plantsAmount; i++) {
            addPlant();
        }
    }

    public synchronized List<Animal> getAvailableAnimals(@NotNull Animal animal) {
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
