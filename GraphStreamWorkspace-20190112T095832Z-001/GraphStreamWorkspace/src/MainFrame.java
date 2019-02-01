import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel jPanel;
    private JButton generateButton;
    private JTextField vertexField;
    private JTextField edgesField;
    private JPanel SettingsPanel;
    private JTextField vertexesInCykleField;
    private JButton generateAndSearchCykleButton;

    public MainFrame()
    {
        add(jPanel);
        setTitle("Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    if(Double.parseDouble(edgesField.getText()) > 1)
                    {
                        JOptionPane.showMessageDialog(jPanel, "Prawdopodobieństwo powinno być z przedziału 0-1 oraz być zapisane w notacji z kropką (np. 0.5)");

                    }
                    else if(Integer.parseInt(vertexField.getText()) > 120){
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120");

                    }
                    else
                App.displayGraph(Integer.parseInt(vertexField.getText()),Double.parseDouble(edgesField.getText()));
                }
                catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120.\nPrawdopodobieństwo powinno być z przedziału 0 - 1 oraz być zapisane w notacji z kropką (np. 0.5)");
                }

            }
        });
        generateAndSearchCykleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if(Double.parseDouble(edgesField.getText()) > 1)
                    {
                        JOptionPane.showMessageDialog(jPanel, "Prawdopodobieństwo powinno być z przedziału 0-1 oraz być zapisane w notacji z kropką (np. 0.5)");

                    }
                    else if(Integer.parseInt(vertexField.getText()) > 120){
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120");

                    }
                    else if(Integer.parseInt(vertexesInCykleField.getText()) > Integer.parseInt(vertexField.getText()) ) {
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków w cyklu nie może być większa niż ilość wierzchołków grafu");
                    }
                    else if(Integer.parseInt(vertexesInCykleField.getText()) < 3 || Integer.parseInt(vertexesInCykleField.getText()) > 6){
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków w cyklu musi zawierać się w przedziale: 3 - 6");
                    }
                    else
                        App.displayGraph(Integer.parseInt(vertexField.getText()),Double.parseDouble(edgesField.getText()));
                }
                catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120.\nPrawdopodobieństwo powinno być z przedziału 0 - 1 oraz być zapisane w notacji z kropką (np. 0.5)");
                }

            }
        });
    }
}
