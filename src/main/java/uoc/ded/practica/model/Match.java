package uoc.ded.practica.model;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioAVLImpl;

public class Match {

    private String matchId;
    private Game game;
    private int totalUsersInMatch = 0;

    private Diccionario<String, PlayerScore> usersInMatch;

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
