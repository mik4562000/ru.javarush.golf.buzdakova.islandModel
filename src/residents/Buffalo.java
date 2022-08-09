package residents;

public class Buffalo extends Herbivore{
    {
        weight = 700;
        maxNumberPerLocation = 10;
        movementSpeed = 3;
        foodSaturationWeight = 100;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC03";
    }
}
