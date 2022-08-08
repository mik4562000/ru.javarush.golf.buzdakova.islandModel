package residents;

import java.util.Map;

public class Duck extends Herbivore{
    {
        WEIGHT = 1;
        MAX_NUMBER_PER_LOCATION = 200;
        MOVEMENT_SPEED = 4;
        FOOD_SATURATION_WEIGHT = 0.15;
        foodProbability = Map.of(
                Caterpillar.class, 90);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
