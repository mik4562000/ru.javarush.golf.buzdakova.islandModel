package residents;

public class Caterpillar extends Herbivore{
    {
        WEIGHT = 0.01;
        MAX_NUMBER_PER_LOCATION = 1000;
        MOVEMENT_SPEED = 0;
        FOOD_SATURATION_WEIGHT = 0;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC1B";
    }
}
