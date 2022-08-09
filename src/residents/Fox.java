package residents;

import java.util.Map;

public class Fox extends Predator {
    {
        weight = 8;
        maxNumberPerLocation = 30;
        movementSpeed = 2;
        foodSaturationWeight = 2;
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
