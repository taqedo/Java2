package lesson1;

public class Team {

    String name;
    Contestants[] team;

    public Team(String name, Contestants... contestant) {
        this.name = name;
        this.team = contestant;
    }

    public void info(){
        for (Contestants x : team) {
            if(x.isOnDistRun()) System.out.println(x.name + " прошел испытания бег!");
            else System.out.println(x.name + " не прошел испытания бег");
            if (x.isOnDistJump()) System.out.println(x.name + " прошел испытания прыжки");
            else System.out.println(x.name + " не прошел испытания прыжки");
            if (x.isOnDistSwim()) System.out.println(x.name + " проплыл бассейн");
            else System.out.println(x.name + " не проплыл бассейн");
        }
    }

    public void whoDone() {
        System.out.println("В команде " + this.name + " испытания прошли:");
        for (Contestants x : team) {
            if (x.isOnDist()) System.out.println(x.name);

        }
    }


    public Contestants[] getTeam() {
        return team;
    }
}
