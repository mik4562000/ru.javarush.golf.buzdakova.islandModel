package residents;

public class Caterpillar extends Herbivore{
    {
        weight = 0.01;
        maxNumberPerLocation = 1000;
        movementSpeed = 0;
        foodSaturationWeight = 0;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC1B";
    }
}
