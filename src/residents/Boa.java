package residents;

import java.util.Map;

public class Boa extends Predator {
    {
        weight = 15;
        maxNumberPerLocation = 30;
        movementSpeed = 1;
        foodSaturationWeight = 3;
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
