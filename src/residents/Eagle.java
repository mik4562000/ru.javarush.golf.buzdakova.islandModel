package residents;

import java.util.Map;

public class Eagle extends Predator {
    {
        weight = 6;
        maxNumberPerLocation = 20;
        movementSpeed = 3;
        foodSaturationWeight = 1;
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
