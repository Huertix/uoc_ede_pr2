# 2019-1 - DED - PRÁCTICA
### Implementador: David Huerta



####Notas de aclaración:

- El código no está fuertemente comentado, pues se ha seguido la estrategia de nombrar a las funciones y a los atributos de tal forma que expresen con claridad sus responsabilidades.
No obstante, alli donde el código puede tener cierta complejida, se pueden encontrar comentarios que ayudan a su comprensión.

- El uso de la negación en la condicionales ha sido sustituido por una comparación directa al valor **'false'**. Esto ayuda a su visibilidad.

- Los métodos implementados procedentes de la interface Play4Fun, no tienen JavaDoc puesto que la interface ya dispone de descripciones.

- La llamada a algunos métodos desde la clase **Play4FunImpl** puede generar excepciones que, por definición del contrato con su interface, no deben propagarse. Dichas excepciones
han sido capturadas con bloques try-catch, pero por el momento no se hace ningún tipo de gestión o logging.

- Este ejercicio se puede encontrar almacenado en el siguiente [repositorio](https://github.com/Huertix/uoc_ede_pr2).

- Una comparación de los cambios en el código entre las versiones EP2-Práctica se puede encontrar en el siguiente [link](https://github.com/Huertix/uoc_ede_pr2/compare/EP2...master?expand=1).


####Estructura del proyecto:
~~~
.
├── lib
│   ├── hamcrest-core-1.3.jar
│   ├── junit-4.12.jar
│   └── tads_cast.jar
├── readme.md
├── src
│   ├── main
│   │   └── java
│   │       └── uoc
│   │           └── ded
│   │               └── practica
│   │                   ├── Play4Fun.java
│   │                   ├── Play4FunImpl.java
│   │                   ├── exceptions
│   │                   │   ├── DEDException.java
│   │                   │   ├── GameAlreadyExistsException.java
│   │                   │   ├── GameNotFoundException.java
│   │                   │   ├── LevelAlreadyExistsException.java
│   │                   │   ├── LevelFullException.java
│   │                   │   ├── LevelNotFoundException.java
│   │                   │   ├── MatchAlreadyExistsException.java
│   │                   │   ├── MatchNotFoundException.java
│   │                   │   ├── NoEnoughPointsException.java
│   │                   │   ├── ScreenNotFoundException.java
│   │                   │   ├── UserNotFoundException.java
│   │                   │   └── UserNotInMatchException.java
│   │                   ├── model
│   │                   │   ├── Game.java
│   │                   │   ├── Level.java
│   │                   │   ├── Match.java
│   │                   │   ├── Message.java
│   │                   │   ├── Move.java
│   │                   │   ├── PlayerScore.java
│   │                   │   ├── Screen.java
│   │                   │   └── User.java
│   │                   ├── tads
│   │                   │   ├── Games.java
│   │                   │   ├── MatchMessages.java
│   │                   │   ├── TopPlayedGames.java
│   │                   │   ├── TopPlayers.java
│   │                   │   └── VectorOrdenado.java
│   │                   └── util
│   │                       └── DateUtils.java
│   └── test
│       └── java
│           └── uoc
│               └── ded
│                   └── practica
│                       ├── FactoryPlay4Fun.java
│                       ├── Play4FunEP2Test.java
│                       ├── Play4FunPRATest.java
│                       └── Play4FunTest.java
├── test.cmd
└── test.sh


~~~


####Estructura de las clases:

#####La clase **Play4FunImpl**, contiene directamente las siguiente estructuras relevantes:

- **Games**: Es una tabla de dispersión, la cual implementa Diccionario. En ella se almacena los juegos. La busqueda de un juego tiene un coste constante.
 
- **Users**: Es una arbol AVL. Se pueden llegar a almacenar un gran volumen de usuarios. Costes logarítmicos para las consultas.

- **multiPlayerGames**: Es una arbol AVL. Se pueden llegar a almacenar un gran volumen de partidas **'Match'**. Costes logarítmicos para las consultas.

- **TopPlayedGames**: Es una lista encadena ordenada. Se almacenan los juegos de mayor a menor en función del número de partidas jugadas.

#####La clase **Match**, contiene directamente las siguiente estructuras:

- **usersInMatch**: Es una arbol AVL. Se almacena la lista de usuarios que juegan una partida multijugador. Costes logarítmicos para las consultas.

- **MatchMessages**: Es una lista encadenada ordena. Almacena los mensajes públicos y privados enviados en una partida multijugador. Se almacena ordenadamente
teniendo en cuenta la fecha de envio.


#####La clase **Game**, contiene directamente las siguiente estructuras:

- **Levels**: Es una lista encadena. Se almacena los niveles de un juego. La busqueda de un nivel se realiza con la ayuda de un iterador.

#####La clase **Level**, contiene directamente las siguiente estructuras:

- **Screens**: Es una vector acotado. Contiene las pantallas de un nivel. La inserción, busqueda y actulización se realiza por la ayuda del identificador de la pantalla, que sirve como indice en el vector.

#####La clase **Screen**, contiene directamente las siguiente estructuras:

- **TopPlayers**:  Esta clase extiende de la clase VectorOrdenado, la cual implementa ContenedorAcotado. En ella se almacena los 10 usuarios con mejor puntuación obtenida en dicha pantalla, ordenados por puntos
obtenidos de mayor a menor. Hace uso de la clase **Move** la cual ayuda a la gestión de usurios que juegan una pantalla.


####UnitTest:
~~~
$ java -cp \
> ./out/test/2019-1-EP2PRA2019OtonnoEnunciado:\
> ./out/production/2019-1-EP2PRA2019OtonnoEnunciado:\
> ./lib/hamcrest-core-1.3.jar:./lib/junit-4.12.jar:\
> ./lib/tads_cast.jar\
>  org.junit.runner.JUnitCore\
>   uoc.ded.practica.Play4FunTest

JUnit version 4.12
......................
Time: 0.057

OK (22 tests)

~~~

####UnitTest from test.sh:
~~~
$ ./test.sh 

>> Compilant : 


>> Executant JUnit tests : 

JUnit version 4.12
......................
Time: 0.052

OK (22 tests)

~~~
