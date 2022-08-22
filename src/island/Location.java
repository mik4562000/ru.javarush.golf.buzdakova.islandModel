package island;

import org.jetbrains.annotations.NotNull;
import residents.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private List<Animal> animals;
    private final List<Animal> appearedAnimals;
    private final List<Animal> departedAnimals;
    private final Random random = new Random();

    public Location(int x, int y) {
        coordinates = new Coordinates(x, y);
        this.plants = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.appearedAnimals = new ArrayList<>();
        this.departedAnimals = new ArrayList<>();
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
        for (Animal animal : animals) {
            if (!animal.isPresent()) continue;

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

    public void remove(Animal animal) {
        animal.setPresent(false);
    }

    public void depart(Animal animal) {
        departedAnimals.add(animal);
    }

    public void removePlant() {
        plants.remove(0);
    }

    public synchronized void add(Animal animal) {
        long sameAnimalCount = getLiveAnimals().stream().filter(a -> a.getClass() == animal.getClass()).count();
        if (sameAnimalCount < animal.getMaxNumberPerLocation()) {
            appearedAnimals.add(animal);
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
        long sameAnimalsCount;

        sameAnimalsCount = getLiveAnimals().stream().filter(a -> a.getClass() == animal.getClass() && a.isPresent()).count();

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

    public List<Animal> getAvailableAnimalsForFood(@NotNull Animal animal) {
        if (animal.getFoodProbability() == null) return null;
        List<Animal> foodList = new ArrayList<>();
        for (Animal food : getLiveAnimals()) {
            if (animal.isPresent() && animal.getFoodProbability().containsKey(food.getClass())) {
                foodList.add(food);
            }
        }
        return foodList.isEmpty() ? null : foodList;
    }

    private synchronized List<Animal> getLiveAnimals() {
        return Stream.concat(animals.stream(), appearedAnimals.stream())
                .filter(a -> a.isPresent())
                .filter(a -> !(departedAnimals.contains(a)))
                .collect(Collectors.toList());
    }

    public void prepareAnimalListForNewDay() {
        animals = getLiveAnimals().stream().toList();
        appearedAnimals.clear();
        departedAnimals.clear();
    }

}
