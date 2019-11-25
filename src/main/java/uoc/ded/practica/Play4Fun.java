package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.models.Game;
import uoc.ded.practica.models.Move;
import uoc.ded.practica.models.User;
import uoc.ei.tads.Iterador;

/**
 * Definición del TAD de gestión de la plataforma de juegos para tablets y móviles
 */
public interface Play4Fun {


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
            throws DEDException;


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
    public User getUser(String idUser) ;
}


