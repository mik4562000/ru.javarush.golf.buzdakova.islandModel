package residents;

import java.util.Map;

public class Boar extends Herbivore{
    {
        weight = 400;
        maxNumberPerLocation = 50;
        movementSpeed = 2;
        foodSaturationWeight = 50;
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
