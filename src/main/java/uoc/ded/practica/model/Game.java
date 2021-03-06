package uoc.ded.practica.model;

import uoc.ded.practica.exceptions.LevelAlreadyExistsException;
import uoc.ded.practica.exceptions.LevelNotFoundException;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;

import java.util.Objects;

public class Game {
    private String id;
    private String description;
    private int totalPlayed;
    private Lista<Level> levels;
    private int totalLevels;
    private PlayerScore maxScoredPlayer = null;

    public Game(String id, String description) {
        this.id = id;
        this.description = description;
        this.levels = new ListaEncadenada<Level>();
        this.totalLevels = 0;
    }

    public String getIdGame() {
        return id;
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

    public Iterador<Level> getLevels() {
        return this.levels.elementos();
    }

    /**
     *  Function to search a level based in the level ID
     * @param String levelId
     * @return Level
     * @throws LevelNotFoundException
     */
    public Level getLevel(String levelId) throws LevelNotFoundException {
        final Iterador<Level> levels = this.levels.elementos();
        while (levels.haySiguiente()) {
            Level currentLevel = levels.siguiente();
            if (currentLevel.getId().equals(levelId))
                return currentLevel;
        }

        throw new LevelNotFoundException();
    }

    /**
     * Add new Level to Game. If level exists, it throws an error
     * @param level
     * @throws LevelAlreadyExistsException
     */
    public void addLevel(Level level) throws LevelAlreadyExistsException {
        try {
            this.getLevel(level.getId());
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

    public PlayerScore getMaxScoredPlayer() {
        return maxScoredPlayer;
    }

    public void setMaxScoredPlayer(PlayerScore maxScoredPlayer) {
        this.maxScoredPlayer = maxScoredPlayer;
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
