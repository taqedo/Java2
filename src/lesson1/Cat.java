package lesson1;

public class Cat extends Contestants {
    private boolean isOnDist;

    public Cat(String name, int runDist, int jumpHeight, int swimDist) {
        super(name, runDist, jumpHeight, swimDist);
        isOnDist = false;

    }

    public boolean isOnDist() {
        return isOnDist;
    }

    public void setOnDist(boolean onDist) {
        isOnDist = onDist;
    }
}
