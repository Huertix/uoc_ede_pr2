package uoc.ded.practica.models;

import uoc.ded.practica.exceptions.LevelAlreadyExistsException;
import uoc.ded.practica.exceptions.LevelNotFoundException;
import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;

import java.util.Objects;

public class Game {

    private String id;
    private String description;
    private int totalPlayed;
    /* lista encadenada normal*/
    private Lista<Level> levels;
    private int totalLevels;

    public Game(String id, String description) {
        this.id = id;
        this.description = description;
        this.levels = new ListaEncadenada<Level>();
        this.totalLevels = 0;
    }

    public String getIdGame() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalPlayed() {
        return totalPlayed;
    }

    public void increaseTotalPlayed() {
        this.totalPlayed += 1;
    }

    public Contenedor<Level> getLevels() {
        return levels;
    }

    public Level getLevel(String levelId) throws LevelNotFoundException {
        final Iterador<Level> levels = this.levels.elementos();
        while (levels.haySiguiente()) {
            Level currentLevel = levels.siguiente();
            if (currentLevel.getId().equals(levelId))
                return currentLevel;
        }

        throw new LevelNotFoundException();
    }

    public void addLevel(Level level) throws LevelAlreadyExistsException {
        try {
            Level requestedLevel = this.getLevel(level.getId());
            throw new LevelAlreadyExistsException();
        } catch (LevelNotFoundException e) {
            this.levels.insertarAlFinal(level);
            this.increaseTotalLevels();
        }
    }

    public int getTotalLevels() {
        return totalLevels;
    }

    public void increaseTotalLevels() {
        this.totalLevels += 1;
    }

    public void reduceTotalLevels() {
        this.totalLevels -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return totalPlayed == game.totalPlayed &&
                Objects.equals(id, game.id) &&
                Objects.equals(description, game.description) &&
                Objects.equals(levels, game.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, totalPlayed, levels);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
