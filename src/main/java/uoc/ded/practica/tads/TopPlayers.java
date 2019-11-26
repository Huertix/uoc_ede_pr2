package uoc.ded.practica.tads;

import uoc.ded.practica.models.Move;

public class TopPlayers extends VectorOrdenado<Move> {

    public TopPlayers(int max) {
        this.maxElementos = max;
        this.elementos = (Move[]) new Move[max];
        this.nElementos = 0;
        this.primero = 0;
    }

    public void updatePlayers(Move move) {
        if (this.estaVacio()) {
            this.elementos[0] = move;
            this.nElementos += 1;
            return;
        }

        int index;

        for (index = this.nElementos - 1; index >= 0; index--) {
            Move currentMove =  this.elementos[index];

            this.elementos[index + 1] = currentMove;
            if (currentMove.getPoints() > move.getPoints())
                break;
        }
        this.elementos[index + 1] = move;
        this.nElementos += 1;
    }
}
