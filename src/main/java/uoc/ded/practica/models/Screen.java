package uoc.ded.practica.models;

import uoc.ded.practica.Play4Fun;
import uoc.ded.practica.tads.TopPlayers;
import uoc.ei.tads.Iterador;

import java.util.Objects;

public class Screen {

    private int id;
    private int minPoints;
    private String name;
    private TopPlayers topPlayersInScreen;

    public Screen(int id, int minPoints) {
        this.id = id;
        this.minPoints = minPoints;
        topPlayersInScreen = new TopPlayers(Play4Fun.TOP_GAMES);
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

    /**
     * Update and sort the list of top 10 user in the screen bases in user's points.
     * @param move Helper function for user and Game
     */
    public void updateTopPlayersInScreen(Move move) {
        this.topPlayersInScreen.updatePlayers(move);
    }

    /**
     *
     * @return Iterator with the top 10 users in screen.
     */
    public Iterador<Move> getTopUsersForScreen() {
        return this.topPlayersInScreen.elementos();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screen screen = (Screen) o;
        return id == screen.id &&
                minPoints == screen.minPoints &&
                Objects.equals(name, screen.name) &&
                Objects.equals(topPlayersInScreen, screen.topPlayersInScreen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minPoints, name, topPlayersInScreen);
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", minPoints=" + minPoints +
                ", name='" + name + '\'' +
                '}';
    }
}
