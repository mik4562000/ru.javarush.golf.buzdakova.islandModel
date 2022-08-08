package island;

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
            animal.move(this, null);
        }
    }

    public void remove(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant() {
        plants.remove(0);
    }

    public void add(Animal animal) {
        animals.add(animal);
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
            animals.add((Animal) babyAnimal);
        }
    }

    public void generatePopulation() {
        // loop for all possible animals
        // for every animal get number and create this number of animals
        Random random = new Random();
        for (var clazz : animalsPopulation) {
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getConstructor();
                Animal newAnimal = (Animal) constructor.newInstance();
                animals.add(newAnimal);
                int numberPerLocation = random.nextInt((Integer) clazz.getDeclaredMethod("getMaxNumberPerLocation", null).invoke(newAnimal, null));
                for (int i = 0; i < numberPerLocation; i++) {
                    animals.add((Animal) constructor.newInstance());
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAvailableAnimals(Animal animal) {
        //get all available animals for this animal to eat
        List<Animal> foodList = new ArrayList<>();
        for (Animal food : animals) {
            if (animal.foodProbability.containsKey(food.getClass())) {
                foodList.add(food);
            }
        }
        return foodList;
    }


}