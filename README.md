# GraphStream 

Projekt aplikacji na Algorytmiczne ujęcie teori Grafów.
Aplikacja generuje graf i znajduje w nim trójelementowe cykle oraz podświetla je przez zadany czas. 

# Jak uruchomić aplikację?

Aby uruchomić aplikację należy przejść do folderu "Jar from xxx"* i uruchomić w nim consolę (CMD dla Windows) lub terminal (linux, mac) i uruchomić program komendą: 
`
java -jar GraphStreamWorkspace.jar 
`

*Alternatywnie można przejść do lokalizacji: /GraphStream/GraphStreamWorkspace-20190112T095832Z-001/GraphStreamWorkspace/out/artifacts  i wybrać interesujący nas plik .jar.

# Dane wyjściowe

Po każdorazowym wygenerowaniu grafu, jego dane* są dopisywane do pliku output.txt, który znajduje się w tej samej lokalizacji co plik .jar (jeżeli go nie ma, to zostanie wygenerowany automatycznie).

*dane wyjściowe - data wygenerowania, ilośc wierzchołków, ilość krawędzi, sąsiedzi wierzchołków, macierz sąsiedztwa, cykle. 
