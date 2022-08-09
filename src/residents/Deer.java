package residents;

public class Deer extends Herbivore{
    {
        weight = 300;
        maxNumberPerLocation = 20;
        movementSpeed = 4;
        foodSaturationWeight = 50;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8C";
    }
}
