package uoc.ded.practica.models;

public class Move {

    private User user;
    private int points;

    public Move(User user, int points) {
        this.user = user;
        this.points = points;
    }

    public User getUser() {
        return this.user;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
