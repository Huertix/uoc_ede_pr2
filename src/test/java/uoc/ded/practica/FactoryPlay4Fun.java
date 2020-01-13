package uoc.ded.practica;


import uoc.ded.practica.exceptions.DEDException;

public class FactoryPlay4Fun {


    public static Play4Fun getMyLimon() throws Exception {
        Play4Fun play4Fun;
        play4Fun = new Play4FunImpl();

        populate4EP2(play4Fun);
        return play4Fun;
    }


    protected static void populate4EP2( Play4Fun play4Fun) throws DEDException {
        ////
        //// USERS
        ////
        play4Fun.addUser("idUser1", "Joan", "Simo");
        play4Fun.addUser("idUser2", "Pep", "Lluna");
        play4Fun.addUser("idUser3", "Isma", "Ferra");
        play4Fun.addUser("idUser4", "Marc", "Quilez");
        play4Fun.addUser("idUser5", "Armand", "Morata");
        play4Fun.addUser("idUser6", "Jesus", "Sallent");
        play4Fun.addUser("idUser7", "Anna", "Casals");
        play4Fun.addUser("idUser8", "Mariajo", "Padró");
        play4Fun.addUser("idUser9", "Agustí", "Padró");
        play4Fun.addUser("idUser10", "Pepet", "Marieta");

        ////
        //// GAMES
        ////
        play4Fun.addGame("idGame1", "Description 1");
        play4Fun.addGame("idGame2", "Description 2");
        play4Fun.addGame("idGame3", "Description 3");
        play4Fun.addGame("idGame4", "Description 4");
        play4Fun.addGame("idGame5", "Description 5");
        play4Fun.addGame("idGame6", "Description 6");


        ////
        //// LEVELS
        ////
        play4Fun.addLevel("idLevel1", "idGame1", "LEVEL1", 10, 6);
        play4Fun.addLevel("idLevel2", "idGame1", "LEVEL1", 5, 2);


        ////
        //// SCREENS
        play4Fun.addScreen("idGame1", "idLevel1", 0, 25);
        play4Fun.addScreen("idGame1", "idLevel1", 1, 20);
        play4Fun.addScreen("idGame1", "idLevel1", 2, 20);
    }


    protected static void populate4PRA(Play4Fun play4Fun) throws DEDException{

        //// USERS

        play4Fun.addUser("idUser101", "Maria", "Lluna");
        play4Fun.addUser("idUser102", "Izan", "Ferra");
        play4Fun.addUser("idUser103", "Carlo", "Quilez");
        play4Fun.addUser("idUser104", "Adrià", "Morata");
        play4Fun.addUser("idUser105", "Ale", "Jandra");

        //// MATCHES
        play4Fun.addMatch("idMatch1", "idGame1");
        play4Fun.addMatch("idMatch2", "idGame2");
        play4Fun.addMatch("idMatch3", "idGame3");
        play4Fun.addMatch("idMatch4", "idGame4");
        play4Fun.addMatch("idMatch5", "idGame1");

        play4Fun.playGame("idUser101","idGame1" );
        play4Fun.playGame("idUser102","idGame1" );
        play4Fun.playGame("idUser103","idGame1" );
        play4Fun.playGame("idUser104","idGame1" );
        play4Fun.playGame("idUser105","idGame1" );

        play4Fun.joinMatch("idMatch1", "idUser101");
        play4Fun.joinMatch("idMatch1", "idUser102");
        play4Fun.joinMatch("idMatch1", "idUser103");
        play4Fun.joinMatch("idMatch1", "idUser104");
        play4Fun.joinMatch("idMatch1", "idUser105");

        play4Fun.joinMatch("idMatch5", "idUser101");
        play4Fun.joinMatch("idMatch5", "idUser102");
        play4Fun.joinMatch("idMatch5", "idUser103");

    }


}