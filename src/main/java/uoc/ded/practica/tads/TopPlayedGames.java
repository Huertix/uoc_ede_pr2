package uoc.ded.practica.tads;

import uoc.ei.tads.*;
import uoc.ded.practica.models.Game;

/* lista encadenada ordenada */
// TODO: convertir a lista encadena ordenada
public class TopPlayedGames extends ListaEncadenada<Game> {

    /*
    Para guardar los juegos más jugados usaremos una ​lista encadenada ordenada​
    ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos
    devolver los juegos ordenados de mayor a menor en función del número de partidas
    jugadas la lista deberá ser ordenada. Se podría considerar también el uso de un
    ​vector ordenado​ ya que en este caso sólo desperdiciamos espacio hasta que se haya
    jugado una partida de cada juego.
     */
    public TopPlayedGames() {
        super();
    }

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

            if (game.getIdGame().equals(currentGame.getIdGame())) {
                this.borrar(next);
            }

        }

    }

}
