package uoc.ded.practica;

import uoc.ded.practica.Play4Fun;

public class FactoryPlay4Fun {

    public static Play4Fun getMyLimon() throws Exception {
        Play4Fun play4Fun;
        play4Fun = new Play4FunImpl();

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
        play4Fun.addLevel("idLevel1","idGame1", "LEVEL1", 10, 6 );
        play4Fun.addLevel("idLevel2","idGame1", "LEVEL1", 5, 2 );


        ////
        //// SCREENS
        play4Fun.addScreen("idGame1", "idLevel1", 0, 25);
        play4Fun.addScreen("idGame1", "idLevel1", 1, 20);
        play4Fun.addScreen("idGame1", "idLevel1", 2, 20);


        return play4Fun;
    }


}