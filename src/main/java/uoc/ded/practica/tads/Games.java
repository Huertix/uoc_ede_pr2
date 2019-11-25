package uoc.ded.practica.tads;

import uoc.ded.practica.exceptions.DEDException;
import uoc.ded.practica.exceptions.GameAlreadyExistsException;
import uoc.ded.practica.exceptions.GameNotFoundException;
import uoc.ded.practica.models.Game;
import uoc.ei.tads.ExcepcionContenedorLleno;
import uoc.ei.tads.ExcepcionTADs;
import uoc.ei.tads.Iterador;

import java.util.Comparator;

public class Games extends VectorOrdenado<Game> {

    public Games(int max) {
        this.maxElementos = max;
        this.elementos = (Game[]) new Game[max];
        this.nElementos = 0;
        this.primero = 0;
    }

    public void insertSorted(Game newGame) throws DEDException {

        if(this.estaLleno())
            throw new ExcepcionContenedorLleno();

        if (this.estaVacio()) {
            this.elementos[0] = newGame;
            this.nElementos += 1;
            return;
        }

        int index;

        for (index = this.nElementos - 1; index >= 0; index--) {
            Game currentGame = this.elementos[index];

            if (currentGame.getIdGame().equalsIgnoreCase(newGame.getIdGame()))
                throw new GameAlreadyExistsException();

            this.elementos[index + 1] = currentGame;
            if (currentGame.getIdGame().compareToIgnoreCase(newGame.getIdGame()) < 0) {
                break;
            }
        }
        this.elementos[index + 1] = newGame;
        this.nElementos += 1;

    }

    public Iterador<Game> getTopPlayedGames() {
        return null;
    }

    public Game getGame(String idGame) throws GameNotFoundException {
        if (this.estaVacio()) {
            throw new GameNotFoundException();
        }

        final Iterador games = this.elementos();

        while (games.haySiguiente()) {
            Game currentGame = (Game) games.siguiente();
            if (currentGame.getIdGame().equals(idGame))
                return currentGame;
        }

        throw new GameNotFoundException();
    }
}
