package residents;

public class Deer extends Herbivore{
    {
        WEIGHT = 300;
        MAX_NUMBER_PER_LOCATION = 20;
        MOVEMENT_SPEED = 4;
        FOOD_SATURATION_WEIGHT = 50;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8C";
    }
}
