package uoc.ded.practica.model;

import uoc.ded.practica.exceptions.LevelFullException;
import uoc.ded.practica.exceptions.ScreenNotFoundException;

import java.util.Arrays;
import java.util.Objects;

public class Level {

    private String id;
    private String name;
    private int hardness;
    private int nScreens;
    private int totalScreens;
    private Screen[] screens;

    public Level(String id, String name, int hardness, int nScreens) {
        this.id = id;
        this.name = name;
        this.hardness = hardness;
        this.nScreens = nScreens;
        this.screens = new Screen[nScreens];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public int getnScreens() {
        return nScreens;
    }

    public void setnScreens(int nScreens) {
        this.nScreens = nScreens;
    }

    public int getTotalScreens() {
        return totalScreens;
    }

    public void increaseTotalScreens() {
        this.totalScreens += 1;
    }

    public void reduceTotalScreens() {
        this.totalScreens -= 1;
    }

    public Screen[] getScreens() {
        return screens;
    }

    /**
     *  Get the screen from level based on levelId
     * @param String levelScreenId
     * @return Screen
     * @throws ScreenNotFoundException
     */
    public Screen getScreen(int levelScreenId) throws ScreenNotFoundException {
        try {
            return screens[levelScreenId];
        } catch (Exception e){
            // Potentially could throw and index of range exception
            throw new ScreenNotFoundException();
        }

    }

    /**
     * Insert screen using the screenID directly in the array index position
     * @param Screen screen to store or update.
     * @throws LevelFullException
     */
    public void insertScreen(Screen screen) throws LevelFullException {
        if (screen.getId() >= screens.length)
            throw new LevelFullException();
        try {
            // We update the screen. We can update just the points, but we update the full object instead
            screens[screen.getId()] = screen;
            this.increaseTotalScreens();
        } catch (Exception e) {
            // Potentially could throw and index of range exception
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return hardness == level.hardness &&
                nScreens == level.nScreens &&
                Objects.equals(id, level.id) &&
                Objects.equals(name, level.name) &&
                Arrays.equals(screens, level.screens);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, hardness, nScreens);
        result = 31 * result + Arrays.hashCode(screens);
        return result;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hardness=" + hardness +
                ", nScreens=" + nScreens +
                '}';
    }
}
