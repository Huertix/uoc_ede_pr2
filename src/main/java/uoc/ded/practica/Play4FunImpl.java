package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.*;
import uoc.ded.practica.tads.Games;
import uoc.ded.practica.tads.TopPlayedGames;
import uoc.ei.tads.*;

import java.util.Date;

public class Play4FunImpl implements Play4Fun {

    private Games games;
    private Lista<User> users;
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
    public void addGame(String idGame, String description) throws GameAlreadyExistsException {
        Game newGame = new Game(idGame, description);
        this.games.insertSorted(newGame);
    }

    @Override
    public void addLevel(String idLevel, String idGame, String name, int hardness, int nLevelScreens)
            throws LevelAlreadyExistsException {
        Game game = null;
        try {
            game = this.games.getGame(idGame);
        } catch (GameNotFoundException e) {
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
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
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
        }

        Screen newScreen = new Screen(idScreen, points);
        level.insertScreen(newScreen);
    }

    @Override
    public void playGame(String idUser, String idGame) {
        User user = getUser(idUser); // We do nothing with user by now

        Game game = null;
        try {
            game = this.games.getGame(idGame);
            game.increaseTotalPlayed();
        } catch (GameNotFoundException e) {
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
        }

        this.topPlayedGames.updateTopPlayedGames(game);
    }

    @Override
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points)
            throws NoEnoughPointsException {
        User user = this.getUser(idUser); // Buscar el usuario en la lista encadenada de usuarios => O(U).
        Move move = new Move(user, points);

        Game game = null;
        Level level = null;
        try {
            game = this.games.getGame(idGame);  // Buscar el juego al vector ordenado de juegos => O (logJ ).
            level = game.getLevel(idLevel); // Buscar el nivel en la lista encadenada de niveles => O (NJ)
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
        }

        Screen screen = null;
        try {
            screen = level.getScreen(levelScreenId); // Buscar la pantalla al vector de pantallas del nivel => O (1)
        } catch (ScreenNotFoundException e) {
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
        }

        // Comprobar si el número de puntos es mayor que el requerido => O (1)
        if (points < screen.getMinPoints()) {
            throw new NoEnoughPointsException();
        }

        screen.updateTopPlayersInScreen(move);
    }

    @Override
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID)
            throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException {
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
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
        }

        return game.getTotalLevels();
    }

    @Override
    public int numScreens(String idGame, String idLevel) {
        Level level = null;
        try {
            Game game = this.games.getGame(idGame);
            level = game.getLevel(idLevel);
        } catch (GameNotFoundException | LevelNotFoundException e) {
            e.printStackTrace(); // Because of our contract with the interface, we catch this exception and we stop the propagation here
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

    @Override
    public void addMatch(String matchID, String gameID) throws MatchAlreadyExistsException, GameNotFoundException {

    }

    @Override
    public void joinMatch(String matchID, String userID) throws MatchNotFoundException {

    }

    @Override
    public void kill(String matchID, String killerID, String killedID, int points) {

    }

    @Override
    public PlayerScore topUserForGame(String gameID) {
        return null;
    }

    @Override
    public void sendPublicMessage(String matchID, String userID, String message, Date date) throws MatchNotFoundException {

    }

    @Override
    public void sendPrivateMessage(String matchID, String senderID, String receiverID, String message, Date date) throws MatchNotFoundException {

    }

    @Override
    public Iterador<Message> publicMessages(String matchID) throws MatchNotFoundException {
        return null;
    }

    @Override
    public Iterador<Message> privateMessages(String matchID, String userID) throws MatchNotFoundException, UserNotFoundException, UserNotInMatchException {
        return null;
    }

    @Override
    public int numMatches() {
        return 0;
    }

    @Override
    public int numUsersByMatch(String matchID) {
        return 0;
    }

    @Override
    public PlayerScore getPlayerFromMatch(String matchId, String userId) {
        return null;
    }
}
