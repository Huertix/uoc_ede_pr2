package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.Game;
import uoc.ded.practica.model.Move;
import uoc.ded.practica.model.User;
import uoc.ei.tads.Iterador;

/**
 * Definición del TAD de gestión de la plataforma de juegos para tablets y móviles
 */
public interface Play4Fun {


    /**
     * Dimensión estimada del contenedor de juegos
     *
     */
    public static final int G = 1000;

    /**
     * dimensión del contenedor de juegos
     */
    public static final int J = 250;


    /**
     * dimensión del contenedor de las pantallas del nivel
     */
    public static final int PN = 8;

    /**
     * dimensión del contenedor de topPlayers
     */
    public static int TOP_GAMES = 10;


    /**
     * Método que añade un nuevo usuario en el sistema
     *
     * @param idUser  identificador del usuario
     * @param name    nombrebre del usuario
     * @param surname apellido del usuario
     * @pre cierto
     * @post si el código de usuario es nuevo, los usuarios serán
     * los mismos más un nuevo usuario con los datos indicados.
     * Sino los datos del usuario se habrán actualizado con los nuevos.
     *
     */
    public void addUser(String idUser, String name, String surname);


    /**
     * Método que permite añadir un nuevo juego en el sistema
     *
     * @param idGame identificador del juego
     * @param description descripción del juego
     *
     * @pre cierto
     * @post  los juegos son los mismos más uno nuevo con los datos
     * indicados. Si ya hay un juego con el nombre especificado
     * devolverá un error.
     */
    public void addGame(String idGame, String description)
            throws GameAlreadyExistsException;


    /**
     * Método que permite añadir un nuevo nivel a un juego especificado
     *
     * @param idLevel identificador del nivel
     * @param idGame identificador del juego
     * @param name nombre del juego
     * @param hardness nivel de dificultat
     * @param nLevelScreens número de pantalles que tendrá
     *
     * @pre existe un juego con el idGame especificat
     * @post los niveles son los mismos más uno nuevo con los
     * datos indicados. Si ya hay un nivel con el identificador
     * especificado devolverá un error.
     */
    public void addLevel(String idLevel, String idGame, String name, int hardness, int nLevelScreens)
            throws LevelAlreadyExistsException;


    /**
     * Mètode que permet afegir una pantall a un nivelde un juego
     *
     * @param idGame identificador del juego
     * @param idLevel identificador del nivel
     * @param idScreen identificador de la pantalla
     * @param points número de puntos necesarios para pasar la pantalla
     *
     * @pre existe un juego con el gameID y el levelID especificats.
     * @post Si levelScreenID no existe, el nivel identificado con
     * levelID del juego gameID tiene las mismas pantallas más una
     * nueva con los datos indicados. Sino los datos de la pantalla
     * habrán actualizado con las nuevas. Si el nivel ya tiene todas
     * las pantallas definidas devolverá un error.
     *
     * @throws LevelFullException
     */
    public void addScreen(String idGame, String idLevel, int idScreen, int points )
            throws LevelFullException;


    /**
     * Mètode que permet jugar a una nova partida
     *
     * @param idUser identificador del jugador
     * @param idGame identificador del juego
     *
     * @pre existe un usuario con el userID especificado y un
     * juego con el gameID especificado.
     * @post  se ha actualizado la estructura de los 10 juegos más utilizados
     *
     */
    public void playGame(String idUser, String idGame);


    /**
     * Mètode que permet passar una pantalla en un juego
     *
     * @param idUser identificador del usuario
     * @param idGame identificador del juego
     * @param idLevel identificador del nivel
     * @param levelScreenId identificador del nivel dentro de la pantalla
     * @param points punts aconseguits
     *
     * @pre existe un usuario, un juego, un nivel y una pantalla con
     * los identificadores especificados.
     * @post Si el número de puntos conseguidos es menor que el número de
     * puntos que se requiere para pasar la pantalla devolverá un
     * error. Si el número de puntos conseguidos es mayor que el
     * número de puntos que se requiere para pasar la pantalla se
     * actualiza la estructura que guarda los usuarios que han pasado la pantalla.
     */
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points)
            throws NoEnoughPointsException;


    /**
     * Mètode que proporciona un iterador con els 10 jugadors que han passat la
     * pantalla ordenats de major a menor segons la puntuació obtinguda.
     *
     * @param idGame identificador del juego
     * @param idLevel identificador del nivel
     * @param levelScreenID identificador de la pantalla
     * @return
     * @throws GameNotFoundException en caso que no exista el juego
     * @throws LevelNotFoundException en caso que no exista el nivel
     *
     * @pre cierto
     * @post devuelve un iterador para recorrer los 10 jugadores que han pasado
     * la pantalla ordenados de mayor a menor según la puntuación obtenida.
     * Si el juego, el nivel o la pantalla dentro del nivel no existen
     * devolverá un error.
     */
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID)
            throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException;


    /**
     * Mètode que proporciona un iterador con els juegos més utilitzats de major a menor
     * @return returna l'iterador de juegos
     *
     * @pre  cierto
     * @post devuelve un iterador para recorrer los juegos más utilizados
     * ordenados de mayor a menor.
     */
    public Iterador<Game> topGames();


    /**
     * Mètode que proporciona el nombrebre de usuarios
     * @return retorna el total de usuarios
     */
    public int numUsers();

    /**
     * Mètode que proporciona el nombrebre de juegos
     * @return retorna el total de juegos
     */
    public int numGames();


    /**
     * Mètode que proporciona el nombrebre de nivelsde un juego
     * @param idGame el juego
     * @return retorna el nivelde un juego. Si el juego no existe retorna 0
     */
    public int numLevels(String idGame);

    /**
     * Mètode que proporciona el nombrebre de pantallesde un nivelde un juego
     * @param idGame el juego
     * @param idLevel el nivel
     * @return retorna el nombrebre de pantalles de un nivel de un juego. Si el juego o
     * nivel no existe, retorna 0
     */
    public int numScreens(String idGame, String idLevel);

    /**
     * Mètode que proporciona un usuari identificat
     *
     * @param idUser identificador del usuario
     * @return retorna l'usuari identificcat per idUser o null en caso que no exista
     */
    public User getUser(String idUser);




    ////////
    // PRA
    ////////



    /**
     * Método que añade una partida al sistema
     *
     * @pre cierto.
     * @post las partidas serán las mismas más una nueva para el juego indicado. Si ya existe
     * la partida o el juego no existe devolverá un error.
     */
    public void addMatch(String matchID, String gameID) throws MatchAlreadyExistsException, GameNotFoundException;

    /**
     * Método que permite añadir un jugador a una partida
     *
     * @pre existe un jugador con el identificador userID especificado, no está jugando
     *      la partida especificada con matchID pero sí está en el Juego (playGame) de la partida
     * @post los jugadores de la partida especificadas serán los mismos más el jugador
     *      especificado con el identificador userID. Si no existe la partida devolverá un error.
     */
    public void joinMatch(String matchID, String userID) throws MatchNotFoundException;


    /**
     * Método que permite matar un jugador de una partida
     *
     @pre la partida especificada con matchID y los jugadores killerID y killedID que se
     especifican en la llamada existen, no son el mismo jugador y están jugando la partida matchID.
     @post los jugadores de la partida especificada con matchID serán los mismos
     menos el jugador especificado con el identificador killedID. El jugador especificado
     con killerID incrementara sus puntos en la partida con el valor especificado en
     points. Si solo queda el jugador identificado con killerID en la partida esta termina,
     se debe actualizar el jugador con mayor puntuación del juego y las partidas serán
     las mismas menos la partida identificada con matchID.
     */
    public void kill(String matchID, String killerID, String killedID, int points);


    /**
     * Método que proporciona el usuario con más puntuación de las partidas de ese juego
     *
     * @pre el juego identificado con gameID existe
     * @post Devuelve el usuario que ha obtenido mayor puntuación en las partidas multijugador
     * jugadas para este juego junto con su puntuación.
     */
    public PlayerScore topUserForGame(String gameID);


    /**
     * Método que genera un mensaje público asociado a una partida
     *
     * @pre el jugador userID existe y está jugando la partida matchID.
     * @post Los mensajes de la partida matchID son los mismo más un nuevo mensaje
     * con el contenido message enviado por el usuario userID. Si la partida
     * identificada con matchID no existe devuelve un error.
     */
    public void sendPublicMessage(String matchID, String userID, String message, Date date)
            throws MatchNotFoundException;


    /**
     * Método que genera un mensaje privado entre jugadores de una partida
     *
     * @pre los jugadores identificados con senderID y receiverID existen y están jugando la partida matchID.
     * @post Los mensajes recibidos por el jugador identificados con receiverID
     * de la partida matchID son los mismos más un nuevo mensaje con el contenido
     * message enviado por el usuario senderID. Si la partida identificada con matchID
     * no existe devuelve un error.
     * */
    public void sendPrivateMessage(String matchID, String senderID, String receiverID, String message, Date date)
            throws MatchNotFoundException;

    /**
     * Método que proporciona los mensajes públicos de una partida
     * @pre cierto.
     * @post devuelve un iterador con los mensajes públicos enviados a una partida
     * ordenados por orden de envío. Si
     * la partida identificada con matchID no existe devuelve un error.
     */
    public Iterador<Message> publicMessages(String matchID) throws MatchNotFoundException;

    /**
     *  Método que proporciona los mensajes privados recibidos de un jugador
     *  @pre cierto.
     *  @post devuelve un iterador los mensajes recibidos por el jugador identificado con
     *  userID ordenados por orden de envío. Si la partida identificada con matchID o el
     *  jugador identificado con userID no existe o no está jugando la partida en este
     *  momento devuelve un error.
     */
    public Iterador<Message> privateMessages(String matchID, String userID)
            throws MatchNotFoundException, UserNotFoundException, UserNotInMatchException;



    /**
     * Método que proporciona el número de partidas que hay en el sistema
     * @return el número de partidas
     */
    public int numMatches();


    /**
     * Método que proporciona el número de jugadores de una partida
     * @param matchID
     * @return el número de jugadores que se han añadido a una partida, cero en caso
     * que no exista la partida
     */
    public int numUsersByMatch(String matchID);


    /**
     * método que proporciona un usuario de una partida
     * @param matchId identificador de la partida
     * @param userId identificador del usuari
     * @return retorna el usuario de la partida, en caso que no exista retorna null
     */
    public PlayerScore getPlayerFromMatch(String matchId, String userId);
}


