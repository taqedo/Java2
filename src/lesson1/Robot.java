package lesson1;

public class Robot extends Contestants  {
    private boolean isOnDist;

    public Robot(String name, int runDist, int jumpHeight, int swimDist) {
        super(name, runDist, jumpHeight, swimDist);
    }

    public boolean isOnDist() {
        return isOnDist;
    }

    public void setOnDist(boolean onDist) {
        isOnDist = onDist;
    }



}

