package residents;

import java.util.Map;

public class Bear extends Predator {
    {
        weight = 500;
        maxNumberPerLocation = 5;
        movementSpeed = 2;
        foodSaturationWeight = 80;
        foodProbability = Map.of(
                Mouse.class, 90,
                Boa.class, 80,
                Deer.class, 80,
                Rabbit.class, 80,
                Goat.class, 70,
                Sheep.class, 70,
                Boar.class, 50,
                Horse.class, 40,
                Buffalo.class, 20,
                Duck.class, 10);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3B";
    }

}
