package residents;

public class Rabbit extends Herbivore{
    {
        weight = 2;
        maxNumberPerLocation = 150;
        movementSpeed = 2;
        foodSaturationWeight = 0.45;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
