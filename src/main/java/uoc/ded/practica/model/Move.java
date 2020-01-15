package uoc.ded.practica.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Move{" +
                "user=" + user +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return points == move.points &&
                Objects.equals(user, move.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, points);
    }
}
