package uoc.ded.practica.models;

import uoc.ded.practica.Play4Fun;
import uoc.ded.practica.tads.TopPlayers;
import uoc.ei.tads.Iterador;

public class Screen {

    private int id;
    private int minPoints;
    private String name;

    /*  ​vector ordenado​
     * ordenado por la puntuacion de usarios en la pantalla
     */
    // TODO: refactorizar a Vector Ordenado. No hace falta Busqueda Dicotomica
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

    public void updateTopPlayersInScreen(Move move) {
        this.topPlayersInScreen.updatePlayers(move);
    }

    public Iterador<Move> getTopUsersForScreen() {
        return this.topPlayersInScreen.elementos();
    }
}
