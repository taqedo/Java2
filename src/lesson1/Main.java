package lesson1;

public class Main {


    public static void main(String[] args) {

//        Obstacles:

        Racetrack rt1 = new Racetrack("Sprint",200);
        Wall w1 = new Wall("Wall",5);
        SwimmingPool sp1 = new SwimmingPool("Pool",100);

        Racetrack rt2 = new Racetrack("Marathon",1000);
        Wall w2 = new Wall("BF'Wall",10);
        SwimmingPool sp2 = new SwimmingPool("River",200);



//        Contestants:

        Human cont1 = new Human("Isaac", 2000, 100, 400);
        Human cont2 = new Human("Habib", 3600, 300, 500);
        Human cont3 = new Human("Jordan", 3000, 200, 300);

        Cat cont4 = new Cat("Barzik", 1000, 200, 160);
        Cat cont5 = new Cat("Murzik", 500, 180, 200);

        Robot cont6 = new Robot("Bender", 5000, 100, 100);
        Robot cont7 = new Robot("Blender", 1000, 300, 200);
        Robot cont8 = new Robot("Cop", 2000, 250, 220);

        Team t1 = new Team("Life party", cont1, cont3, cont4, cont5);
        Team t2 = new Team("Machines", cont2, cont6, cont7, new Human("Sam", 1000, 100, 200));

        Course course1 = new Course("Полоса препятствий 1", rt1, w1, sp1);
        Course course2 = new Course("Олимпийская полоса препятствий", rt2, w2, new SwimmingPool("river", 250), new Wall("WAAALL", 15));


//        Competition:

        course1.doIt(t1);
        course1.doIt(t2);
        t1.whoDone();
        t2.whoDone();

        course2.doIt(t1);
        course2.doIt(t2);

        t1.info();
        t2.info();

        t1.whoDone();
        t2.whoDone();


    }
}
