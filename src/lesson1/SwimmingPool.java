package lesson1;

public class SwimmingPool extends Obstacle{
    private String name;
    private int length;

    public SwimmingPool(String name, int length) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

}
