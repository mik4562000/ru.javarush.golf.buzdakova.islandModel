package island;

import residents.Wolf;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(100, 20);
        island.print();
        //island.liveAnotherDay();
        //island.print();
        Wolf wolf = new Wolf();
        //System.out.println(wolf.getWeight());
        wolf.print();


    }

}
