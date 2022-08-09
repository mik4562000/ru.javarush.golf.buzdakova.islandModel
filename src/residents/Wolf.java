package residents;

import java.util.Map;

public class Wolf extends Predator {
    {
        weight = 50;
        maxNumberPerLocation = 30;
        movementSpeed = 3;
        foodSaturationWeight = 8;
        foodProbability = Map.of(
                Mouse.class, 80,
                Sheep.class, 70,
                Rabbit.class, 60,
                Goat.class, 60,
                Duck.class, 40,
                Deer.class, 15,
                Boar.class, 15,
                Horse.class, 10,
                Buffalo.class, 10);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
