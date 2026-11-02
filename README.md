# tp3-terminal
Trabajo practico 3 de catedra Programacion II profesor Augusto Cesar Fernandez Carbonell

Integrantes: Simon Delmenico, Lucas Sarasola, Luciano Dindart.

## Como se compila: 
En la carpeta raiz del proyecto, ejecutar estos comandos: 
```
javac -d out src/terminal/modelo/*.java src/terminal/app/Main.java
java -cp out terminal.app.Main
```

## Preguntas: 

### 1. Elijan una excepción propia y expliquen por qué la hicieron checked o unchecked. La respuesta tiene que hablar de este programa, no de la definición.


Respuesta: AsientoOcupadoException: Le hicimos checked por que, es necesario checkear siempre si el asiento que quiere el pasajero esta disponible o no, es algo que no recae sobre el programador sino el entorno.

### 2. La lista de espera se usó con dos tipos. ¿Qué habría que haber escrito sin genericidad?

Respuesta: Sin genericidad tendriamos que haber creado una clase para cada tipo distinto o usar una clase mas primitiva como ArrayDeque que no tendria nuestras funciones propias.

### 3. ¿Por qué Pasajero necesita hashCode, si ningún informe lo llama?

 Respuesta: Necesita el hashCode, por que si alguna coleccion necesitase saber si hay duplicados podria usar equals o hashCode para saber si es identico a otro objeto  


