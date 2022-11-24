package lesson1;

public abstract class Contestants {
    private boolean isOnDistSwim = false;
    private boolean isOnDistRun = false;
    private boolean isOnDistJump = false;
    private boolean isOnDist = false;
    protected String name;
    protected int runDist;
    protected int jumpHeight;
    protected int swimDist;

    public Contestants(String name, int runDist, int jumpHeight, int swimDist) {
        this.name = name;
        this.runDist = runDist;
        this.jumpHeight = jumpHeight;
        this.swimDist = swimDist;
    }
    public boolean run(int runDist) {
        if (this.runDist >= runDist) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean jump(int jumpDist) {
        if (this.jumpHeight >= jumpDist) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean swim (int swimDist) {
        if (this.swimDist >= swimDist) {
            return true;
        }
        else{
            return false;
        }
    }
    public Contestants() {
    }
    public boolean isOnDistSwim() {
        return isOnDistSwim;
    }

    public void setOnDistSwim(boolean onDistSwim) {
        isOnDistSwim = onDistSwim;
    }

    public boolean isOnDistRun() {
        return isOnDistRun;
    }

    public void setOnDistRun(boolean onDistRun) {
        isOnDistRun = onDistRun;
    }

    public boolean isOnDistJump() {
        return isOnDistJump;
    }

    public void setOnDistJump(boolean onDistJump) {
        isOnDistJump = onDistJump;
    }

    public boolean isOnDist() {
        return isOnDist;
    }

    public void setOnDist(boolean onDist) {
        isOnDist = onDist;
    }

}
