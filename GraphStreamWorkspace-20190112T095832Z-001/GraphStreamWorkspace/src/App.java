import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;


import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class App {

    private static void generateOutput(Graph graph) {
        //Generowanie wyjścia do pliku txt

        Date date = new Date();

        System.out.println("-----------------------------------------");
        System.out.println("Autg. Sem.5 | Wyszukiwanie cykli\n");
        System.out.println("Zespol: D.Czopek / T.Dziubinski / P.Zajac\n");
        System.out.println("Data wygenerowania: " + date + "\n");
        System.out.println("Ilość wierzchołków: " + graph.getNodeCount());
        System.out.println("Ilość krawędzi: " + graph.getEdgeCount());
        System.out.println();

        Collection<Node> nodesList = graph.getNodeSet();

        for (Node node : nodesList) {
            System.out.print("Sasiedzi wierzcholka \"" + node.getId() + "\" : ");
            Iterator itr = node.getNeighborNodeIterator();

            while (itr.hasNext()) System.out.print(itr.next() + "; ");
            System.out.println();
        }

        System.out.println("\nMacierz sasiedztwa: \n");
        System.out.print("   ");

        for (Node node : nodesList) System.out.print(node.getId() + "  ");
        System.out.println();
        System.out.print("  ");
        for (Node node : nodesList) System.out.print("---");
        System.out.println();

        for (Node node : nodesList) {
            System.out.print(node + " |");
            for (Node checkedNode : nodesList) {
                if (node == checkedNode) System.out.print("0, ");
                else {
                    if (node.hasEdgeBetween(checkedNode)) System.out.print("1, ");
                    else System.out.print("0, ");
                }
            }
            System.out.println();
        }

    }

    private static List<String> getNodesNamesList(int vertices) {

        String auxName;
        Integer alphabetIterator = 0;
        List<String> nodesNames = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            auxName = "";
            if (alphabetIterator == 0)
                auxName += (char) (i % 26 + 65);
            else {
                auxName += ((char) (i % 26 + 65)) + (alphabetIterator.toString());
            }

            nodesNames.add(auxName);

            if (i % 26 == 25) alphabetIterator++;
        }

        return nodesNames;
    }

    private static Graph generateSingleGraph(int vertices, double probability) {

        Graph graph = new SingleGraph("Tutor 1");
        List<String> nodeNames = getNodesNamesList(vertices);

        for (int i = 0; i < vertices; i++) {
            graph.addNode(nodeNames.get(i));
            graph.getNode(nodeNames.get(i)).setAttribute("ui.label", nodeNames.get(i));
        }

        for (int j = 0; j < vertices; j++) {
            Random rand = new Random();

            for (int k = 0; k < vertices; k++) {

                if ((rand.nextDouble() < probability) && (j < k)) {
                    graph.addEdge(nodeNames.get(j) + nodeNames.get(k), nodeNames.get(j), nodeNames.get(k));
                }
            }

        }

        graph.addAttribute("ui.stylesheet"," graph { fill-color: black; } node { size: 15px; fill-color: #44F; stroke-mode: plain; stroke-width: 2px; stroke-color: #CCF; shadow-mode: gradient-radial; shadow-width: 3px; shadow-color: #EEF, #000; shadow-offset: 0px; text-alignment: at-right; text-padding: 3px, 2px; text-background-mode: rounded-box; text-background-color: #EB2; text-color: #222;} edge { fill-color: #CCF; size: 2px;}");
        return graph;
    }

    static void displaySimpleGraph(int vertices, double probability) {

        Graph graph = generateSingleGraph(vertices, probability);
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        generateOutput(graph);
    }

    static void displayGraphWithCycles(int vertices, double probability, long highlightingTime) throws InterruptedException {

        Graph graph = generateSingleGraph(vertices, probability);
        List<String> nodesNames = getNodesNamesList(vertices);
        List<List<String>> cycleNodes = new ArrayList<>();

        String currentNodeId;


        for (String node : nodesNames) {

            currentNodeId = node;

            List<String> currentNodeNeighbours = new ArrayList<>();
            Iterator itr = graph.getNode(currentNodeId).getNeighborNodeIterator();

            while (itr.hasNext()) {
                currentNodeNeighbours.add(itr.next().toString());
            }


            for (String neighbourNode : currentNodeNeighbours) {
                Iterator nItr = graph.getNode(neighbourNode.toString()).getNeighborNodeIterator();

                //Dodawanie listy nodów z cyklu do listy cyklów

                while (nItr.hasNext()) {
                    Object nItrElement = nItr.next();
                    if (currentNodeNeighbours.contains(nItrElement.toString())) {

                        //dodawanie nodów do pojedynczego cyklu

                        List<String> cycle = new ArrayList<>();
                        cycle.add(nItrElement.toString());
                        cycle.add(currentNodeId);
                        cycle.add(neighbourNode);

                        //sortowanie w celu sprawdzenia czy dany cykl jest już w liście cykli

                        var sortedList = cycle.stream().sorted().collect(Collectors.toList());
                        if (!(cycleNodes.contains(sortedList)))
                            cycleNodes.add(sortedList);
                    }
                }


            }


        }

        generateOutput(graph);
        System.out.println("\nLista wierzcholkow cykli trojelementowych: \n");
        for (List<String> cycle : cycleNodes) {
            System.out.print(cycleNodes.indexOf(cycle) + ".: ( ");
            for (String node : cycle) {
                System.out.print(node + ", ");
            }
            System.out.println(")");
        }
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

        //Tworzenie listy krawędzi w cyklach

        new Thread(() ->


        {

            while (true) {
                for (List<String> cycle : cycleNodes) {

                    List<Edge> edgesInCycle = new ArrayList<>();

                    edgesInCycle.add(graph.getNode(cycle.get(0)).getEdgeBetween(cycle.get(1)));
                    edgesInCycle.add(graph.getNode(cycle.get(0)).getEdgeBetween(cycle.get(2)));
                    edgesInCycle.add(graph.getNode(cycle.get(1)).getEdgeBetween(cycle.get(2)));

                    for (Edge edge : edgesInCycle) {
                        edge.addAttribute("ui.style", "fill-color: red; size: 4px;");
                    }

                    for (String node : cycle) {

                        graph.getNode(node).addAttribute("ui.style", "fill-color: red;");
                    }


                    try {
                        Thread.sleep(highlightingTime * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (Edge edge : edgesInCycle) {
                        edge.addAttribute("ui.style", "fill-color: #CCF; size: 2px;");
                    }

                    for (String node : cycle) {

                        graph.getNode(node).addAttribute("ui.style", "fill-color: #44F;");
                    }

                }
            }
        }
        ).start();

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainFrame mainFrame = new MainFrame();

                //Zmiana Streamu wyjściowego na txt

                PrintStream out = null;
                try {
                    out = new PrintStream(new FileOutputStream("output.txt", true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.setOut(out);

                mainFrame.setSize(400, 400);
                mainFrame.setVisible(true);

            }
        });


    }
}
