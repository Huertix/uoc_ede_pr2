package uoc.ded.practica.tads;

import uoc.ei.tads.*;
import uoc.ded.practica.model.Game;


public class TopPlayedGames extends ListaEncadenada<Game> {

    public TopPlayedGames() {
        super();
    }

    /**
     * Update the list of games. The Game is sorted in to the list, based in the number of times
     * that it was played.
     * @param game Game to update
     */
    public void updateTopPlayedGames (Game game) {
        if (this.estaVacio()) {
            this.insertarAlPrincipio(game);
            return;
        }

        final Recorrido<Game> posiciones = this.posiciones();

        boolean inserted = false;
        while(posiciones.haySiguiente()) {
            Posicion<Game> next = posiciones.siguiente();
            final Game currentGame = next.getElem();

            if (game.getTotalPlayed() >= currentGame.getTotalPlayed() && inserted == false) {
                this.insertarAntesDe(next, game);
                inserted = true;
            }

            // Sanity clean
            if (game.getIdGame().equals(currentGame.getIdGame())) {
                this.borrar(next);
            }
        }
    }
}
