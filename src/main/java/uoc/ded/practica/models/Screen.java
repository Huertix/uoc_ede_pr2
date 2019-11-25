package uoc.ded.practica.models;

public class Screen {

    private int id;
    private int minPoints;
    private String name;

    /*  ​vector ordenado​
     * ordenado por la puntuacion de usarios en la pantalla
     */
    private User[] top10UsersInScreen;

    public Screen(int id, int minPoints) {
        this.id = id;
        this.minPoints = minPoints;
    }

    public int getId() {
        return id;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
