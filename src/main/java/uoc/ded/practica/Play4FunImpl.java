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

    private Move[] moves;

    /* lista encadenada​ */
    private Lista<User> users;

    /* lista encadenada ordenada
    *  ordenados de mayor a menor
    * en función del número de partidas jugadas
    * */
    private TopPlayedGames topPlayedGames;


    public Play4FunImpl() {
        this.games = new Games(this.J);
        this.users = new ListaEncadenada<User>();
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

    }

    @Override
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points) throws NoEnoughPointsException {
        User user = this.getUser(idUser);

        Game game = null;
        Level level  = null;
        try {
            game = this.games.getGame(idGame);
            level = game.getLevel(idLevel);
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace();
        }

        Screen screen = level.getScreen(levelScreenId);

        if (points > screen.getMinPoints()) {
            // user to next level
            return;
        }

        throw new NoEnoughPointsException();
    }

    @Override
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID) throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException {
        return new IteradorVectorImpl<Move>(moves, this.numGames(), 0);
    }

    @Override
    public Iterador<Game> topGames() {
        return null;
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
