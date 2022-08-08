package residents;

import java.util.Map;

public class Fox extends Predator {
    {
        WEIGHT = 8;
        MAX_NUMBER_PER_LOCATION = 30;
        MOVEMENT_SPEED = 2;
        FOOD_SATURATION_WEIGHT = 2;
        foodProbability = Map.of(
                Mouse.class, 90,
                Rabbit.class, 70,
                Duck.class, 60,
                Caterpillar.class, 40);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
