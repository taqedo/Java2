package lesson1;

public class Racetrack extends Obstacle{
    private String name;
    private int length;

    public Racetrack(String name, int length) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }


}
