package uoc.ded.practica.model;

import uoc.ded.practica.tads.MatchMessages;
import uoc.ei.tads.*;

import java.util.Date;
import java.util.Objects;

public class Match {

    private String matchId;
    private Game game;
    private int totalUsersInMatch = 0;
    private Diccionario<String, PlayerScore> usersInMatch;
    private MatchMessages matchMessages; // Contains public messages and private messages

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

    public Iterador<Message> getMessagesReceivedByAll() {

        Lista<Message> messagesToAll = new ListaEncadenada<>();

        final Iterador<Message> elementos = matchMessages.elementos();

        while (elementos.haySiguiente()) {
            Message msg = elementos.siguiente();
            if (msg.getReceiver() == null)
                messagesToAll.insertarAlFinal(msg);
        }

        return messagesToAll.elementos();
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", game=" + game +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchId, match.matchId) &&
                Objects.equals(game, match.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, game);
    }
}
