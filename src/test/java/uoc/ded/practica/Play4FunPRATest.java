package uoc.ded.practica;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.Message;
import uoc.ded.practica.model.PlayerScore;
import uoc.ded.practica.util.DateUtils;
import uoc.ei.tads.Iterador;

import java.util.Date;

public class Play4FunPRATest {

    private Play4Fun play4Fun;

    @Before
    public void setUp() throws Exception {
        this.play4Fun = FactoryPlay4Fun.getMyLimon();
        FactoryPlay4Fun.populate4PRA(this.play4Fun);
    }

    @After
    public void tearDown() {
        this.play4Fun = null;
    }

    /**
     * *feature*: (sobre la que hacemos @test): addMatch del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema
     * *scenario*:
     *   - Se añade una nueva partida
     */
    @Test
    public void testAddMatch() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        this.play4Fun.addMatch("idMatch6", "idGame6");
        Assert.assertEquals(6, this.play4Fun.numMatches());
    }

    /**
     * *feature*: (sobre la que hacemos @test): addMatch del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema
     * *scenario*:
     *   - Se añade una nueva partida y ya existe esta nueva partida en el sistema
     */
    @Test (expected = MatchAlreadyExistsException.class)
    public void testAddMatchAndMatchAlreadyExists() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        this.play4Fun.addMatch("idMatch5", "idGame6");
    }

    /**
     * *feature*: (sobre la que hacemos @test): addMatch del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema
     * *scenario*:
     *   - Se añade una nueva partida y no existe el juego
     */
    @Test (expected = GameNotFoundException.class)
    public void testAddMatchAndGameNotFound() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        this.play4Fun.addMatch("idMatch6", "idGameXXXXX");
    }


    /**
     * *feature*: (sobre la que hacemos @test): joinMatch del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se añade un jugador a una partida
     */
    @Test
    public void testJoinMatch() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(5, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(0, this.play4Fun.numUsersByMatch("idMatch2"));


        this.play4Fun.joinMatch("idMatch2", "idUser1");
        Assert.assertEquals(1, this.play4Fun.numUsersByMatch("idMatch2"));
    }

    /**
     * *feature*: (sobre la que hacemos @test): joinMatch del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se añade un jugador a una partida que no existe
     */
    @Test (expected = MatchNotFoundException.class)
    public void testJoinMatchAndMatchNotFound() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(5, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(0, this.play4Fun.numUsersByMatch("idMatch2"));


        this.play4Fun.joinMatch("idMatchXXXX", "idUser1");
    }

    /**
     * *feature*: (sobre la que hacemos @test): kill del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se mata un jugador
     */
    @Test
    public void testKill() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(5, this.play4Fun.numUsersByMatch("idMatch1"));

        this.play4Fun.kill("idMatch1", "idUser101", "idUser102", 15);

        Assert.assertEquals(4, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(5, this.play4Fun.numMatches());

        Assert.assertNull(this.play4Fun.getPlayerFromMatch("idMatch1", "idUser102"));
        Assert.assertEquals(15,
                this.play4Fun.getPlayerFromMatch("idMatch1", "idUser101").getPoints());

    }

    /**
     * *feature*: (sobre la que hacemos @test): kill del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5
     *          jugadores y el partido idMatch5 tiene 3 jugadores
     * *scenario*:
     *   - Se matan todos los jugadores de la partida 1
     *   - Se verifica el mejor jugador del juego
     *   - Se matan todos los jugadores de la partida 5
     *   - Se verifica el mejor jugador del juego
     */
    @Test
    public void testKillAndMatchOverAndTopUserForGame() throws  DEDException {
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(5, this.play4Fun.numUsersByMatch("idMatch1"));

        // MATCH 1: kill 102
        this.play4Fun.kill("idMatch1", "idUser101", "idUser102", 15);

        Assert.assertEquals(4, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(5, this.play4Fun.numMatches());

        Assert.assertNull(this.play4Fun.getPlayerFromMatch("idMatch1", "idUser102"));
        Assert.assertEquals(15,
                this.play4Fun.getPlayerFromMatch("idMatch1", "idUser101").getPoints());

        // MATCH 1: kill 103
        this.play4Fun.kill("idMatch1", "idUser101", "idUser103", 5);

        Assert.assertEquals(3, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(20,
                this.play4Fun.getPlayerFromMatch("idMatch1", "idUser101").getPoints());


        // MATCH 1: kill 104
        this.play4Fun.kill("idMatch1", "idUser101", "idUser104", 25);

        Assert.assertEquals(2, this.play4Fun.numUsersByMatch("idMatch1"));
        Assert.assertEquals(5, this.play4Fun.numMatches());
        Assert.assertEquals(45,
                this.play4Fun.getPlayerFromMatch("idMatch1", "idUser101").getPoints());

        // MATCH 1: kill 105
        this.play4Fun.kill("idMatch1", "idUser101", "idUser105", 1);

        Assert.assertEquals(4, this.play4Fun.numMatches());


        // TOP USER FOR GAME:
        Assert.assertEquals(46,
                this.play4Fun.topUserForGame("idGame1").getPoints());
        Assert.assertEquals("idUser101",
                this.play4Fun.topUserForGame("idGame1").getUser().getId());


        // MATCH 5: kill 102
        this.play4Fun.kill("idMatch5", "idUser103", "idUser101", 50);
        Assert.assertEquals(4, this.play4Fun.numMatches());

        // MATCH 5: kill 103
        this.play4Fun.kill("idMatch5", "idUser103", "idUser102", 60);
        Assert.assertEquals(3, this.play4Fun.numMatches());


        // TOP USER FOR GAME:
        Assert.assertEquals(110,
                this.play4Fun.topUserForGame("idGame1").getPoints());

        Assert.assertEquals("idUser103",
                this.play4Fun.topUserForGame("idGame1").getUser().getId());


    }



    /**
     * *feature*: (sobre la que hacemos @test): sendPrivateMessage y privateMessage del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se envían mensajes privados
     *   - Es consulten els mensajes privados rebuts
     */
    @Test
    public void tesSendPrivateMessage() throws  DEDException {

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 4", DateUtils.createDate("31-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 2", DateUtils.createDate("18-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser102",
                "message 1", DateUtils.createDate("25-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 3", DateUtils.createDate("25-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser103", "idUser101",
                "message 1", DateUtils.createDate("01-12-2019 19:30:00"));


        this.play4Fun.sendPrivateMessage("idMatch1", "idUser103", "idUser102",
                "message 1", DateUtils.createDate("01-12-2019 19:30:00"));

        Iterador<Message> mIdUSer101 = this.play4Fun.privateMessages("idMatch1", "idUser101");

        Message m1 = mIdUSer101.siguiente();
        Assert.assertEquals(DateUtils.createDate("01-12-2019 19:30:00"), m1.getDate());
        Assert.assertEquals("idUser103", m1.getSender().getId());

        Message m2 = mIdUSer101.siguiente();
        Assert.assertEquals(DateUtils.createDate("18-12-2019 19:30:00"), m2.getDate());
        Assert.assertEquals("idUser102", m2.getSender().getId());

        Message m3 = mIdUSer101.siguiente();
        Assert.assertEquals(DateUtils.createDate("25-12-2019 19:30:00"), m3.getDate());
        Assert.assertEquals("idUser102", m3.getSender().getId());

        Message m4 = mIdUSer101.siguiente();
        Assert.assertEquals(DateUtils.createDate("31-12-2019 19:30:00"), m4.getDate());
        Assert.assertEquals("idUser102", m3.getSender().getId());

        Assert.assertFalse(mIdUSer101.haySiguiente());

        Iterador<Message> mIdUSer102 = this.play4Fun.privateMessages("idMatch1", "idUser102");
        Message m5 = mIdUSer102.siguiente();
        Assert.assertEquals(DateUtils.createDate("01-12-2019 19:30:00"), m5.getDate());
        Message m6 = mIdUSer102.siguiente();
        Assert.assertEquals(DateUtils.createDate("25-12-2019 19:30:00"), m6.getDate());


    }


    /**
     * *feature*: (sobre la que hacemos @test): sendPrivateMessage i privateMessage del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se envían mensajes privados
     *   - Es consulten els mensajes privados rebuts i no existeix el jugador
     */
    @Test(expected = UserNotFoundException.class)
    public void tesSendPrivateMessageAndUserNotFound() throws  DEDException {

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 4", DateUtils.createDate("31-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 2", DateUtils.createDate("18-12-2019 19:30:00"));


        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 3", DateUtils.createDate("25-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser103", "idUser101",
                "message 1", DateUtils.createDate("01-12-2019 19:30:00"));


        Iterador<Message> mIdUSer101 = this.play4Fun.privateMessages("idMatch1", "idUserXXXX");
    }

    /**
     * *feature*: (sobre la que hacemos @test): sendPrivateMessage i privateMessage del TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se envían mensajes privados
     *   - Se consultan los mensajes privados recibidos y no existe el jugador en esta partida
     */
    @Test(expected = UserNotInMatchException.class)
    public void tesSendPrivateMessageAndUserNotInMatch() throws  DEDException {

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 4", DateUtils.createDate("31-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 2", DateUtils.createDate("18-12-2019 19:30:00"));


        this.play4Fun.sendPrivateMessage("idMatch1", "idUser102", "idUser101",
                "message 3", DateUtils.createDate("25-12-2019 19:30:00"));

        this.play4Fun.sendPrivateMessage("idMatch1", "idUser103", "idUser101",
                "message 1", DateUtils.createDate("01-12-2019 19:30:00"));


        Iterador<Message> mIdUSer101 = this.play4Fun.privateMessages("idMatch1", "idUser7");
    }


    /**
     * *feature*: (sobre la que hacemos @test): sendPublicMessage TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se consultan los mensajes privados recibidos y no existe el jugador en esta partida
     */
    @Test(expected = MatchNotFoundException.class)
    public void tesSendPublicMessageAndMatchNotFound() throws  DEDException {
        this.play4Fun.sendPublicMessage("idMatchXXX", "id101", "message", DateUtils.createDate("01-12-2019 19:30:00"));
    }

    /**
     * *feature*: (sobre la que hacemos @test): sendPublicMessage i publicMessages TAD Play4Fun
     * *given*: Hay 5 partidas en el sistema. El partido idMatch1 tiene 5 jugadores
     * *scenario*:
     *   - Se consultan los mensajes públicos recibidos
     */
    @Test
    public void tesSendPublicMessage() throws  DEDException {
        this.play4Fun.sendPublicMessage("idMatch1", "idUser101", "message 1", DateUtils.createDate("01-12-2019 19:30:00"));
        this.play4Fun.sendPublicMessage("idMatch1", "idUser101", "message 2", DateUtils.createDate("01-12-2019 20:30:00"));
        Iterador<Message> it = this.play4Fun.publicMessages("idMatch1");

        Message m1 = it.siguiente();
        Assert.assertEquals("message 1", m1.getMessage());

        Message m2 = it.siguiente();
        Assert.assertEquals("message 2", m2.getMessage());

    }

}
