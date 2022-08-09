package residents;

import java.util.Map;

public class Duck extends Herbivore{
    {
        weight = 1;
        maxNumberPerLocation = 200;
        movementSpeed = 4;
        foodSaturationWeight = 0.15;
        foodProbability = Map.of(
                Caterpillar.class, 90);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
