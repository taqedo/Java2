package lesson1;

public class Wall extends  Obstacle{
    private String name;
    private int height;

    public Wall(String name,int height) {
        this.height = height;
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

}
