package residents;

import java.util.Map;

public class Boa extends Predator {
    {
        WEIGHT = 15;
        MAX_NUMBER_PER_LOCATION = 30;
        MOVEMENT_SPEED = 1;
        FOOD_SATURATION_WEIGHT = 3;
        foodProbability = Map.of(
                Mouse.class, 40,
                Rabbit.class, 20,
                Fox.class, 15,
                Duck.class, 10);
    }

    @Override
    public String toString() {
        return " \uD83D\uDC0D";
    }
}
