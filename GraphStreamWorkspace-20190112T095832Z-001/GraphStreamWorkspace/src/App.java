import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;



import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class App {


    public static void displayGraph(int vertexes, double probability){

        String auxName;

        Graph graph = new SingleGraph("Tutor 1");


        List<Node> nodes = new LinkedList<Node>();
        List<Edge> edges = new LinkedList<Edge>();


        for (int i = 0 ; i < vertexes ; i++)
        {
            auxName = Integer.toString(i);
             graph.addNode(auxName);
             nodes.add(graph.getNode(auxName));

            //System.out.println(graph.getNode(auxName).getIndex());

            if(graph.getNode(auxName).getIndex() % 2 == 0 )
                            graph.getNode(auxName).addAttribute("ui.style", "fill-color: red;size: 20px;  stroke-mode: plain; stroke-color: #333;");
                     else
                            graph.getNode(auxName).addAttribute("ui.style", "fill-color: blue; size: 20px; stroke-mode: plain; stroke-color: #333; ") ;

        }

        for(int j = 0 ; j < vertexes ;j++ )
        {
            Random rand = new Random();

            for (int k = 0; k < vertexes; k++) {

                if((rand.nextDouble() < probability)&&(j<k))
                {
                    auxName = Integer.toString(j)+Integer.toString(k);
                    graph.addEdge(auxName,Integer.toString(j),Integer.toString(k));
                    edges.add(graph.getEdge(auxName));

                    graph.getEdge(auxName).setAttribute("ui.style" , "shape: blob; size: 5  px; arrow-shape: none; ");

                }
            }

        }

//        for (Node node : nodes)
//        {
//            System.out.println(node.getId());
//        }

       Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainFrame mainFrame = new MainFrame();

                mainFrame.setSize(400,400);
                mainFrame.setVisible(true);

            }
        });



    }
}
