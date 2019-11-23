package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.models.Game;
import uoc.ded.practica.models.Move;
import uoc.ded.practica.models.User;
import uoc.ei.tads.Iterador;

public class Play4FunImpl implements Play4Fun {
    @Override
    public void addUser(String idUser, String name, String surname) {

    }

    @Override
    public void addGame(String idGame, String description) throws GameAlreadyExistsException {

    }

    @Override
    public void addLevel(String idLevel, String idGame, String name, int hardness, int nLevelScreens) throws LevelAlreadyExistsException {

    }

    @Override
    public void addScreen(String idGame, String idLevel, int idScreen, int points) throws LevelFullException {

    }

    @Override
    public void playGame(String idUser, String idGame) {

    }

    @Override
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points) throws NoEnoughPointsException {

    }

    @Override
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID) throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException {
        return null;
    }

    @Override
    public Iterador<Game> topGames() {
        return null;
    }

    @Override
    public int numUsers() {
        return 0;
    }

    @Override
    public int numGames() {
        return 0;
    }

    @Override
    public int numLevels(String idGame) {
        return 0;
    }

    @Override
    public int numScreens(String idGame, String idLevel) {
        return 0;
    }

    @Override
    public User getUser(String idUser) {
        return null;
    }
}
