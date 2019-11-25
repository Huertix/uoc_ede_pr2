package uoc.ded.practica.tads;

import uoc.ei.tads.ContenedorAcotado;
import uoc.ei.tads.ExcepcionContenedorLleno;
import uoc.ei.tads.ExcepcionTADs;
import uoc.ei.tads.Iterador;
import uoc.ded.practica.models.Game;

public class TopPlayedGames extends VectorOrdenado<Game> {

    public TopPlayedGames(int max) {
        this.maxElementos = max;
        this.elementos = (Game[]) new Game[max];
        this.nElementos = 0;
        this.primero = 0;
    }

    public void addGame(Game game) {
        // check if game was played more than last in collection
        if (this.estaVacio()) {
            this.elementos[0] = game;
            return;
        }

        int index = 0;
        Game[] newGameVector = new Game[this.maxElementos];
        for (Game currentGame : this.elementos) {
            int totalPlaysForGameInVector = currentGame.getTotalPlayed();
            int totalPlaysForGameToAdd = game.getTotalPlayed();

            if (totalPlaysForGameInVector < totalPlaysForGameToAdd) {
                newGameVector[index] = game;
            } else {
                newGameVector[index] = currentGame;
            }
            index++;
        }

    }

    public Iterador<Game> getTopPlayedGames() {
        return null;
    }


}
