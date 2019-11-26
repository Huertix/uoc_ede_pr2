package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.models.*;
import uoc.ded.practica.tads.Games;
import uoc.ded.practica.tads.TopPlayedGames;
import uoc.ei.tads.*;

public class Play4FunImpl implements Play4Fun {

    /* ​
    vector ordenado​ por nombre
    permite realizar búsquedas dicotómicas O(log J)
     */
    private Games games;

    /* lista encadenada​ normal*/
    private Lista<User> users;

    /* lista encadenada ordenada
    *  ordenados de mayor a menor
    * en función del número de partidas jugadas
    * */
    private TopPlayedGames topPlayedGames;


    public Play4FunImpl() {
        this.games = new Games(this.J);
        this.users = new ListaEncadenada<User>();
        this.topPlayedGames = new TopPlayedGames();
    }

    @Override
    public void addUser(String idUser, String name, String surname) {
        final Iterador<User> elementos = this.users.elementos();

        while (elementos.haySiguiente()) {
            User currentUser = elementos.siguiente();
            if (currentUser.getId().equals(idUser)) {
                currentUser.setName(name);
                currentUser.setSurname(surname);
                return;
            }
        }

        User newUser = new User(idUser, name, surname);
        this.users.insertarAlFinal(newUser);

    }

    @Override
    public void addGame(String idGame, String description) throws DEDException {
        Game newGame = new Game(idGame, description);
        this.games.insertSorted(newGame);
    }

    @Override
    public void addLevel(String idLevel, String idGame, String name, int hardness, int nLevelScreens) throws LevelAlreadyExistsException {
        Game game = null;
        try {
            game = this.games.getGame(idGame);
        } catch (GameNotFoundException e) {
            e.printStackTrace();
        }

        Level newLevel = new Level(idLevel, name, hardness, nLevelScreens);
        game.addLevel(newLevel);
    }

    @Override
    public void addScreen(String idGame, String idLevel, int idScreen, int points) throws LevelFullException {
        Level level = null;
        try {
            Game game = this.games.getGame(idGame);
            level = game.getLevel(idLevel);
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace();
        }

        Screen newScreen = new Screen(idScreen, points);
        level.insertScreen(newScreen);
    }

    @Override
    public void playGame(String idUser, String idGame) {

        // TODO aclarar por que uso == false
        // TODO: refactor extract
        // check if user exits
        User user = null;
        final Recorrido<User> posiciones = this.users.posiciones();
        boolean userFound = false;
        while(posiciones.haySiguiente() && userFound == false) {
            final Posicion<User> siguiente = posiciones.siguiente();
            final User currentUser = siguiente.getElem();
            if (currentUser.getId().equals(idUser)) {
                user = currentUser;
                userFound = true;
            }
        }

        Game game = null;
        try {
            game = this.games.getGame(idGame);
            game.increaseTotalPlayed();
        } catch (GameNotFoundException e) {
            e.printStackTrace();
        }

        this.topPlayedGames.updateTopPlayedGames(game);
        // TODO: update user
    }

    @Override
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points) throws NoEnoughPointsException {
        // Buscar el usuario en la lista encadenada de usuarios => O(U).
        User user = this.getUser(idUser);

        Move move = new Move(user, points);

        Game game = null;
        Level level  = null;
        try {
            // Buscar el juego al vector ordenado de juegos => O (logJ ).
            game = this.games.getGame(idGame);
            // Buscar el nivel en la lista encadenada de niveles => O (NJ)
            level = game.getLevel(idLevel);
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace();
        }

        // Buscar la pantalla al vector de pantallas del nivel => O (1)
        Screen screen = null;
        try {
            screen = level.getScreen(levelScreenId);
        } catch (ScreenNotFoundException e) {
            e.printStackTrace();
        }

        // Comprobar si el número de puntos es mayor que el requerido => O (1)
        if (points < screen.getMinPoints()) {
            throw new NoEnoughPointsException();
        }

        screen.updateTopPlayersInScreen(move);
    }

    @Override
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID) throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException {

        final Game game = games.getGame(idGame);
        final Level level = game.getLevel(idLevel);
        final Screen screen = level.getScreen(levelScreenID);

        return screen.getTopUsersForScreen();
    }

    @Override
    public Iterador<Game> topGames() {
        return this.topPlayedGames.elementos();
    }

    @Override
    public int numUsers() {
        return this.users.numElems();
    }

    @Override
    public int numGames() {
        return this.games.numElems();
    }

    @Override
    public int numLevels(String idGame) {

        Game game = null;
        try {
            game = this.games.getGame(idGame);
        } catch (GameNotFoundException e) {
            e.printStackTrace();
        }

        return game.getTotalLevels();
    }

    @Override
    public int numScreens(String idGame, String idLevel) {
        Game game = null;
        Level level = null;

        try {
            game = this.games.getGame(idGame);
            level = game.getLevel(idLevel);
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace();
        }

        return level.getTotalScreens();
    }

    @Override
    public User getUser(String idUser) {
        final Iterador<User> elementos = this.users.elementos();

        while (elementos.haySiguiente()) {
            User currentUser = elementos.siguiente();
            if (currentUser.getId().equals(idUser)) {
                return currentUser;
            }
        }

        return null;
    }
}
