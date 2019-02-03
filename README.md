
# GraphStream 

### (pl)

## O projekcie

Projekt aplikacji na Algorytmiczne ujęcie teori Grafów.
Aplikacja generuje graf i znajduje w nim trójelementowe cykle oraz podświetla je przez zadany czas. 

W projekcie wykorzystano bibliotekę GraphStream: http://graphstream-project.org/

## Jak uruchomić aplikację?

Aby uruchomić aplikację należy przejść do folderu "Jar from xxx"* i uruchomić w nim consolę (CMD dla Windows) lub terminal (linux, mac) i uruchomić program komendą: 
`
java -jar GraphStreamWorkspace.jar 
`

*Alternatywnie można przejść do lokalizacji: /GraphStream/GraphStreamWorkspace-20190112T095832Z-001/GraphStreamWorkspace/out/artifacts  i wybrać interesujący nas plik .jar.

## Dane wyjściowe

Po każdorazowym wygenerowaniu grafu, jego dane* są dopisywane do pliku output.txt, który znajduje się w tej samej lokalizacji co plik .jar (jeżeli go nie ma, to zostanie wygenerowany automatycznie).

*dane wyjściowe - data wygenerowania, ilośc wierzchołków, ilość krawędzi, sąsiedzi wierzchołków, macierz sąsiedztwa, cykle. 

### (eng)

## About

App project for an algorithmic view of the graphs theory.
The application generates a graph, finds three-element cycles in it and highlights them for a given time.

The project uses the GraphStream library: http://graphstream-project.org/

## How to run app?


To run the application, go to the "Jar from xxx"* folder and run the console (CMD for Windows) or terminal (linux, mac) in it then run the program with the command: 
`
java -jar GraphStreamWorkspace.jar 
`

*Alternatively, you can go to location:: /GraphStream/GraphStreamWorkspace-20190112T095832Z-001/GraphStreamWorkspace/out/artifacts  and choose the .jar file that we want to run.

## Data output

Each time a graph is generated, its data* is added to the file output.txt, which is stored at the same location as the .jar file (if there is no such file, it will be generated automatically).

*output data - current date, number of vertices, number of edges, vertices neighbors, neighborhood matrix, cycles.
