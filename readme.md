# 2019-1 - DED - PR2 
### Implementador: David Huerta



####Notas de aclaración:

- El código no está fuertemente comentado, pues se ha seguido la estrategia de nombrar a las funciones y a los atributos de tal forma que expresen con claridad sus responsabilidades.
No obstante, alli donde el código puede tener cierta complejida, se pueden encontrar comentarios que ayudan a su comprensión.

- El uso de la negación en la condicionales ha sido sustituido por una comparación directa al valor **'false'**. Esto ayuda a su visibilidad.

- Los métodos implementados procedentes de la interface Play4Fun, no tienen JavaDoc puesto que la interface ya dispone de descripciones.

- La llamada a algunos métodos desde la clase **Play4FunImpl** puede generar excepciones que, por definición del contrato con su interface, no deben de propagarse. Dichas excepciones
han sido capturadas con bloques try-catch pero por el momento no se hace ningún tipo de gestión.

- Para la clase abstracta **VectorOrdenado<E>**, la PEC1 indica que debería implementar las interfaces ContenedorAcotado y Diccionario. En esta solución se ha omitido la interface Diccionario, 
pues no parece aportar ninguna funcionalidad extra.


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
│   │                   │   ├── NoEnoughPointsException.java
│   │                   │   └── ScreenNotFoundException.java
│   │                   ├── models
│   │                   │   ├── Game.java
│   │                   │   ├── Level.java
│   │                   │   ├── Move.java
│   │                   │   ├── Screen.java
│   │                   │   └── User.java
│   │                   └── tads
│   │                       ├── Games.java
│   │                       ├── TopPlayedGames.java
│   │                       ├── TopPlayers.java
│   │                       └── VectorOrdenado.java
│   └── test
│       └── java
│           └── uoc
│               └── ded
│                   └── practica
│                       ├── FactoryPlay4Fun.java
│                       ├── Play4FunEP2Test.java
│                       └── Play4FunTest.java
├── test.cmd
└── test.sh

~~~


####Estructura de las clases:

#####La clase **Play4FunImpl**, contiene directamente las siguiente estructuras relevantes:

- Games: Esta clase extiende de la clase VectorOrdenado, la cual implementa ContenedorAcotado. En ella se almacena los juegos ordenados por nombre de mayor a menor.
         Para la inserción de un nuevo juego se utiliza una estrategia de busqueda lineal e insercción ordenada.
         Para la busqueda de un juego, se utiliza una estrategia de busqueda dicotómica recursiva con complejidad O(log J).
 
- Users: Es una lista encadena. Se almacena los usuarios de la aplicacion. La busqueda de un usuario se realiza con la ayuda de un iterador.

- TopPlayedGames: Es una lista encadena ordenada. Se almacenan los juegos de mayor a menor en función del número de partidas jugadas.

#####La clase **Game**, contiene directamente las siguiente estructuras:

- Levels: Es una lista encadena. Se almacena los niveles de un juego. La busqueda de un nivel se realiza con la ayuda de un iterador.

#####La clase **Level**, contiene directamente las siguiente estructuras:

- Screens: Es una vector acotado. Contiene las pantallas de un nivel. La inserción, busqueda y actulización se realiza por la ayuda del identificador de la pantalla, que sirve como indice en el vector.

#####La clase **Screen**, contiene directamente las siguiente estructuras:

- TopPlayers:  Esta clase extiende de la clase VectorOrdenado, la cual implementa ContenedorAcotado. En ella se almacena los 10 usuarios con mejor puntuación obtenida en dicha pantalla, ordenados por puntos
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
..........
Time: 0.017

OK (10 tests)

~~~

####UnitTest from test.sh:
~~~
$ ./test.sh 

>> Compilant : 


>> Executant JUnit tests : 

JUnit version 4.12
..........
Time: 0.018

OK (10 tests)

~~~


 