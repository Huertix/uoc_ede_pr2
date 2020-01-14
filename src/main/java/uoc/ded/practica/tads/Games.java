package uoc.ded.practica.tads;

import uoc.ded.practica.exceptions.DEDException;
import uoc.ded.practica.exceptions.GameAlreadyExistsException;
import uoc.ded.practica.exceptions.GameNotFoundException;
import uoc.ded.practica.model.Game;
import uoc.ei.tads.ExcepcionContenedorLleno;
import uoc.ei.tads.TablaDispersion;

public class Games extends TablaDispersion<String, Game> {
    private int maxElementos;
    private int nElementos = 0;

    public Games(int max) {
        super(max);
        this.maxElementos = max;
        this.nElementos = 0;
    }

    /**
     * Function to store a game in table structure
     * @param idGame Game key or gameId to insert
     * @param newGame Game game to insert
     * @throws GameAlreadyExistsException
     */
    public void insert(String idGame, Game newGame) throws GameAlreadyExistsException {

        if (this.nElementos == this.maxElementos)
            throw new ExcepcionContenedorLleno();

        if (this.estaVacio() == false && this.esta(idGame)) {
            throw new GameAlreadyExistsException();
        }

        this.insertar(idGame, newGame);
        this.nElementos += 1;
    }

    /**
     * Public function for getting a requested game
     *
     * @param idGame game to search
     * @return Game game to search
     * @throws GameNotFoundException
     */
    public Game getGame(String idGame) throws GameNotFoundException {
        if (this.estaVacio()) {
            throw new GameNotFoundException();
        }

        // Buscar el juego en la tabla de dispersión de juegos => O(1)
        return this.consultar(idGame);
    }
}
