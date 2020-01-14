package uoc.ded.practica.tads;

import uoc.ded.practica.model.Message;
import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;


public class MatchMessages extends ListaEncadenada<Message> {

    public MatchMessages() {
        super();
    }

    /**
     * Add message in a match sent by users. Message are inserted and sorted by Date
     * that it was played.
     * @param message Message to add
     */
    public void addMessage (Message message) {
        if (this.estaVacio()) {
            this.insertarAlPrincipio(message);
            return;
        }

        final Recorrido<Message> posiciones = this.posiciones();

        boolean inserted = false;
        while(posiciones.haySiguiente()) {
            Posicion<Message> next = posiciones.siguiente();
            final Message currentMessage = next.getElem();

            if (message.getDate().compareTo(currentMessage.getDate()) < 0 && inserted == false) {
                this.insertarAntesDe(next, message);
                inserted = true;
            }
        }

        if (inserted == false) {
            this.insertarAlFinal(message);
        }
    }
}
