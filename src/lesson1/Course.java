package lesson1;

public class Course {
    private String name;
    Obstacle[] course;
    public Course(String name, Obstacle... obstacle) {
        this.name = name;
        this.course = obstacle;
    }
    public void doIt(Team b){
        for (Contestants x : b.team){
            for (Obstacle f : this.course) {
            if (f instanceof Racetrack) {
                if (x.run(((Racetrack) f).getLength())) {
                    x.setOnDistRun(true);
                    x.setOnDist(true);
                } else {
                    x.setOnDistRun(false);
                    x.setOnDist(false);
                    break;
                }
            }
            if (f instanceof  Wall) {
                if (x.jump(((Wall) f).getHeight())) {
                    x.setOnDist(true);
                    x.setOnDistJump(true);
                } else {
                    x.setOnDistJump(false);
                    x.setOnDist(false);
                    break;
                }
            }
            if (f instanceof SwimmingPool) {
                if (x.swim(((SwimmingPool) f).getLength())) {
                    x.setOnDist(true);
                    x.setOnDistSwim(true);
                } else {
                    x.setOnDist(false);
                    x.setOnDistSwim(false);
                    break;
                }
            }
            }


        }



    }
}
