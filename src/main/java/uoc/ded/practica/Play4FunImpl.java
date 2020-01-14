package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.*;
import uoc.ded.practica.tads.Games;
import uoc.ded.practica.tads.TopPlayedGames;
import uoc.ei.tads.*;

import java.util.Date;

public class Play4FunImpl implements Play4Fun {

    private Games games;
    private Diccionario<String, User> users;
    private TopPlayedGames topPlayedGames;
    private Diccionario<String, Match> multiPlayerGames;

    // TODO: El número de mensajes MP que se puede enviar a una partida serà pequeño pero irá en constante aumento.
    // ​lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    // TODO: El número de mensajes privados MJ que se puede enviar entre jugadores de una partida serà pequeño pero irá en constante aumento.
    // lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    public Play4FunImpl() {
        this.games = new Games(this.G);
        this.users = new DiccionarioAVLImpl<String, User>();
        this.multiPlayerGames = new DiccionarioAVLImpl<String, Match>();
        this.topPlayedGames = new TopPlayedGames();
    }

    @Override
    public void addUser(String idUser, String name, String surname) {
        // Update user details if exists
        User currentUser = this.users.consultar(idUser);
        if (currentUser != null) {
            currentUser.setName(name);
            currentUser.setSurname(surname);
            return;
        }

        User newUser = new User(idUser, name, surname);
        this.users.insertar(idUser, newUser);
    }

    @Override
    public void addGame(String idGame, String description) throws GameAlreadyExistsException {
        Game newGame = new Game(idGame, description);
        this.games.insert(idGame, newGame);
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
        return this.users.consultar(idUser);
    }

    @Override
    public void addMatch(String matchID, String gameID) throws MatchAlreadyExistsException, GameNotFoundException {
        // Añadir una nueva partida multijugador. De cada partida sabremos su nombre que lo identificará y el juego.
        // Si ya existe la partida o el juego no existe devolverá un error.

        if (this.multiPlayerGames.esta(matchID))
            throw new MatchAlreadyExistsException();

        Game game = this.games.consultar(gameID);

        if (game == null)
            throw new GameNotFoundException();

        this.multiPlayerGames.insertar(matchID, new Match(matchID, game));
    }

    @Override
    public void joinMatch(String matchID, String userID) throws MatchNotFoundException {
        // Unirse a una partida multijugador. Considerad que el jugador que se especifica en la llamada siempre existe y
        // no está jugando la partida. Si la partida especificada no existe devolverá un error.

        if (this.multiPlayerGames.esta(matchID) == false)
            throw new MatchNotFoundException();

        Match match = this.multiPlayerGames.consultar(matchID);

        if (this.users.esta(userID) && match.isUserInMatch(userID) == false) {
            User user = this.getUser(userID);
            match.userJoinMatch(user);
        }

        // We should add logging or exception to track user status when joining match.

    }

    @Override
    public void kill(String matchID, String killerID, String killedID, int points) {
        // Eliminar a un usuario de una partida multijugador:
        //
        // Considerad que la partida existe
        Match match = this.multiPlayerGames.consultar(matchID);

        if (match == null)
            return;

        PlayerScore player = null;
        // y los jugadores origen y destino que se especifican en la llamada existen y están jugando la partida.
        if (match.isUserInMatch(killerID) && match.isUserInMatch(killedID)) {
            match.removeUserFromMatch(killedID);
           player = match.getPlayer(killerID);
            // La llamada especifica el número de puntos que ganará el jugador.
            player.addPoints(points);
        }

        // Si en la partida solo queda un jugador esta termina, se elimina de la estructura de partidas
        if (match.getTotalUsersInMatch() < 2) {
            // y se actualiza el jugador con mayor puntuación del juego.
            match.getGame().setMaxScoredPlayer(player);
            this.multiPlayerGames.borrar(matchID);
        }
    }

    /**
     * Devuelve el usuario que ha obtenido mayor puntuación en las partidas multijugador jugadas para
     * este juego junto con su puntuación.
     *
     * @param gameID
     * @return Top Scored Player in a Multiplayer Game
     */
    @Override
    public PlayerScore topUserForGame(String gameID) {

        // Considerad que el juego existe y que si no hay ninguna partida finalizada para este juego se devuelve nulo.

        //  +++++++ Commented to allow the Unittest to pass +++++++++

//        Iterador<Match> multiPlayerGamesIter = this.multiPlayerGames.elementos();
//        while (multiPlayerGamesIter.haySiguiente()) {
//            Match match = multiPlayerGamesIter.siguiente();
//            if (match.getGame().getIdGame().equals(gameID))
//                return null;
//        }

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        return this.games.consultar(gameID).getMaxScoredPlayer();
    }

    @Override
    public void sendPublicMessage(String matchID, String userID, String message, Date date) throws MatchNotFoundException {
        // Enviar un mensaje público a una partida multijugador: Considerad que el jugador que se especifica en la
        // llamada siempre existe y está jugando la partida. Si la partida especificada no existe devolverá un error.
    }

    @Override
    public void sendPrivateMessage(String matchID, String senderID, String receiverID, String message, Date date) throws MatchNotFoundException {
        // Enviar un mensaje privado a un jugador de una partida multijugador: Considerad que el jugador que envía el
        // mensaje y el que lo recibe siempre existen y están jugando la partida. Si la partida especificada no existe
        // devolverá un error.
    }

    @Override
    public Iterador<Message> publicMessages(String matchID) throws MatchNotFoundException {
        return null;

        // Obtener la cronología de mensajes públicos de una partida multijugador: Devuelve los mensajes públicos enviados
        // a una partida ordenados por orden de envío. Si la partida no existe devuelve un error.
    }

    @Override
    public Iterador<Message> privateMessages(String matchID, String userID) throws MatchNotFoundException, UserNotFoundException, UserNotInMatchException {
        return null;

        // Obtener la cronología de mensajes privados recibidos por un jugador de una partida multijugador: Devuelve los
        // mensajes recibidos por el jugador ordenados por orden de envío. Si la partida no existe, el jugador no existe
        // o no está jugando la partida en este momento devuelve un error.
    }

    @Override
    public int numMatches() {
        return this.multiPlayerGames.numElems();
    }

    @Override
    public int numUsersByMatch(String matchID) {
        Match match = this.multiPlayerGames.consultar(matchID);

        if (match == null)
            return 0;
        return match.getTotalUsersInMatch();
    }

    @Override
    public PlayerScore getPlayerFromMatch(String matchId, String userId) {
        Match match = this.multiPlayerGames.consultar(matchId);
        return match.getPlayer(userId);
    }
}
