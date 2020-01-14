package uoc.ded.practica.tads;

import uoc.ded.practica.exceptions.DEDException;
import uoc.ded.practica.exceptions.GameAlreadyExistsException;
import uoc.ded.practica.exceptions.GameNotFoundException;
import uoc.ded.practica.model.Game;
import uoc.ei.tads.ExcepcionContenedorLleno;

//TODO: El número de juegos J continúa siendo conocido, pero aumenta mucho.
// no necesitamos ningún recorrido ordenado elegiremos una ​tabla de dispersión​ para conseguir un acceso de consulta constante.
public class Games extends VectorOrdenado<Game> {

    // TODO: Para guardar el usuario con mayor puntuación de un juego multijugador usaremos un atributo en la clase Game.

    public Games(int max) {
        super();
        this.maxElementos = max;
        this.elementos = (Game[]) new Game[max];
        this.nElementos = 0;
        this.primero = 0;
    }

    /**
     * Function to store a game in alphabetical order
     * @param newGame Game game to insert
     * @throws DEDException
     */
    public void insertSorted(Game newGame) throws GameAlreadyExistsException {

        if (this.estaLleno())
            throw new ExcepcionContenedorLleno();

        if (this.estaVacio()) {
            this.elementos[0] = newGame;
            this.nElementos += 1;
            return;
        }

        int index;
        for (index = this.nElementos - 1; index >= 0; index--) {
            Game currentGame = this.elementos[index];

            // Check if game exists already
            if (currentGame.getIdGame().equalsIgnoreCase(newGame.getIdGame()))
                throw new GameAlreadyExistsException();

            this.elementos[index + 1] = currentGame;
            if (currentGame.getIdGame().compareToIgnoreCase(newGame.getIdGame()) < 0) {
                break; // if idGame from array is smaller than newIdGame, exit the loop
            }
        }
        this.elementos[index + 1] = newGame; // add new game in the last index + 1 position calculated from loop
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
        return this.binarySearch(this.elementos, this.nElementos, idGame);
    }

    /**
     * Helper recursive function to search a game using binary Search strategy
     *
     * @param games    array
     * @param nElement number of elements in the array
     * @param idGame   game to search
     * @return Game game to search
     * @throws GameNotFoundException
     */
    private Game binarySearch(Game[] games, int nElement, String idGame) throws GameNotFoundException {
        int middle = (int) Math.floor(nElement / 2);
        Game gameFromArray = games[middle];

        if (gameFromArray.getIdGame().equals(idGame))
            return gameFromArray;

        if (middle < 1)
            throw new GameNotFoundException();

        Game[] part = new Game[middle];
        if (gameFromArray.getIdGame().compareToIgnoreCase(idGame) > 0) {
            // primera mitad
            System.arraycopy(games, 0, part, 0, middle);
        } else {
            // segunda mitad
            System.arraycopy(games, middle + 1, part, 0, middle);
        }

        return this.binarySearch(part, middle, idGame);
    }
}
