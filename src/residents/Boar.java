package residents;

import java.util.Map;

public class Boar extends Herbivore{
    {
        WEIGHT = 400;
        MAX_NUMBER_PER_LOCATION = 50;
        MOVEMENT_SPEED = 2;
        FOOD_SATURATION_WEIGHT = 50;
        foodProbability = Map.of(
                Caterpillar.class, 90,
                Mouse.class, 50
        );
    }

    @Override
    public String toString() {
        return "\uD83D\uDC17";
    }
}
