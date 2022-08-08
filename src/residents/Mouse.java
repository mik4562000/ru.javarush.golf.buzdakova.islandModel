package residents;

import java.util.Map;

public class Mouse extends Herbivore{
    {
        WEIGHT = 0.05;
        MAX_NUMBER_PER_LOCATION = 500;
        MOVEMENT_SPEED = 1;
        FOOD_SATURATION_WEIGHT = 0.01;
        foodProbability = Map.of(
                Caterpillar.class, 90);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC01";
    }
}
