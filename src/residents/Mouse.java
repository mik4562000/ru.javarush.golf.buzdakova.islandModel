package residents;

import java.util.Map;

public class Mouse extends Herbivore{
    {
        weight = 0.05;
        maxNumberPerLocation = 500;
        movementSpeed = 1;
        foodSaturationWeight = 0.01;
        foodProbability = Map.of(
                Caterpillar.class, 90);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC01";
    }
}
