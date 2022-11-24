package lesson1;

public class Human extends Contestants {
    private boolean isOnDist;

    public Human(String name, int runDist, int jumpHeight, int swimDist) {
        super(name, runDist, jumpHeight, swimDist);
    }

    public boolean isOnDist() {
        return isOnDist;
    }

    public void setOnDist(boolean onDist) {
        isOnDist = onDist;
    }
}
