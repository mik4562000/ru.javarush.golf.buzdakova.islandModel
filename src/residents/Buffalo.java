package residents;

public class Buffalo extends Herbivore{
    {
        WEIGHT = 700;
        MAX_NUMBER_PER_LOCATION = 10;
        MOVEMENT_SPEED = 3;
        FOOD_SATURATION_WEIGHT = 100;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC03";
    }
}
