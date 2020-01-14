package uoc.ded.practica.model;

import java.util.Objects;

public class PlayerScore {

    private User user;
    private int points;

    public PlayerScore(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                "user=" + user +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerScore that = (PlayerScore) o;
        return points == that.points &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, points);
    }
}

