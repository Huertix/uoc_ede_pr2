package uoc.ded.practica.model;

import uoc.ded.practica.tads.MatchMessages;
import uoc.ei.tads.*;

import java.util.Date;

public class Match {

    private String matchId;
    private Game game;
    private int totalUsersInMatch = 0;

    private Diccionario<String, PlayerScore> usersInMatch;

    private MatchMessages matchMessages;


    // TODO: El número de mensajes MP que se puede enviar a una partida serà pequeño pero irá en constante aumento.
    // ​lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    // TODO: El número de mensajes privados MJ que se puede enviar entre jugadores de una partida serà pequeño pero irá en constante aumento.
    // lista encadenada ordenada ya que irán en constante aumento y inicialmente estará vacía. Como necesitamos devolver los mensajes en orden cronológico la lista deberá ser ordenada.

    public Match(String matchId, Game game) {
        this.matchId = matchId;
        this.game = game;
        this.usersInMatch = new DiccionarioAVLImpl<String, PlayerScore>();
        this.matchMessages = new MatchMessages();
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

    public void sendMessageToUser(String msg, User sender, User receiver, Date date) {
        Message message = new Message(msg, sender, receiver, date);
        this.matchMessages.addMessage(message);
    }

    public void sendMessageToAll(String msg, User sender, Date date) {
        Message message = new Message(msg, sender, date);
        this.matchMessages.addMessage(message);
    }

    public Iterador<Message> getMessagesReceivedByUser(String userID) {

        Lista<Message> messagesToUser = new ListaEncadenada<>();

        final Iterador<Message> elementos = matchMessages.elementos();

        while (elementos.haySiguiente()) {
            Message msg = elementos.siguiente();
            if (msg.getReceiver().getId().compareTo(userID) == 0)
                messagesToUser.insertarAlFinal(msg);
        }

        return messagesToUser.elementos();
    }
}
