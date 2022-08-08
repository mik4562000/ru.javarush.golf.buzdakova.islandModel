package residents;

import java.util.Map;

public class Eagle extends Predator {
    {
        WEIGHT = 6;
        MAX_NUMBER_PER_LOCATION = 20;
        MOVEMENT_SPEED = 3;
        FOOD_SATURATION_WEIGHT = 1;
        foodProbability = Map.of(
                Rabbit.class, 90,
                Mouse.class, 90,
                Duck.class, 80,
                Fox.class, 10
                );
    }

    @Override
    public String toString() {
        return "\uD83E\uDD85";
    }
}
