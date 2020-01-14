package uoc.ded.practica.model;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioAVLImpl;

public class Match {

    private String matchId;
    private Game game;
    private int totalUsersInMatch = 0;

    private Diccionario<String, PlayerScore> usersInMatch;


    // TODO: El número de mensajes MP que se puede enviar a una partida serà pequeño pero irá en constante aumento.
    // ​lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    // TODO: El número de mensajes privados MJ que se puede enviar entre jugadores de una partida serà pequeño pero irá en constante aumento.
    // lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    public Match(String matchId, Game game) {
        this.matchId = matchId;
        this.game = game;
        this.usersInMatch = new DiccionarioAVLImpl<String, PlayerScore>();
    }

    public void userJoinMatch(User user) {
        if (usersInMatch.esta(user.getId()) == false) {
            usersInMatch.insertar(user.getId(), new PlayerScore(user));
            totalUsersInMatch++;
        }
    }

    public int getTotalUsersInMatch() {
        return this.totalUsersInMatch;
    }

    public boolean isUserInMatch(String userId) {
        return usersInMatch.esta(userId);
    }

    public void removeUserFromMatch(String userId) {
        if (usersInMatch.esta(userId)) {
            usersInMatch.borrar(userId);
            totalUsersInMatch--;
        }
    }

    public PlayerScore getPlayer(String playerId) {
        return usersInMatch.consultar(playerId);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
