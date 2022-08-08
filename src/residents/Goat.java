package residents;

public class Goat extends Herbivore{
    {
        WEIGHT = 60;
        MAX_NUMBER_PER_LOCATION = 140;
        MOVEMENT_SPEED = 3;
        FOOD_SATURATION_WEIGHT = 10;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }
}
