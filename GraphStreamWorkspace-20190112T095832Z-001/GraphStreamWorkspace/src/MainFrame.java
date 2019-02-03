import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel jPanel;
    private JButton generateButton;
    private JTextField vertexField;
    private JTextField probabilityField;
    private JPanel SettingsPanel;
    private JButton generateAndSearchCykleButton;
    private JTextField highlightingTime;

    public MainFrame()
    {
        add(jPanel);
        setTitle("Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Pola wypełniane automatycznie przy uruchomieniu - dla ułatwienia testowania
        //-----------------------------------------------
        vertexField.setText("10");
        probabilityField.setText("0.5");
        //------------------------------------------------

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    if(Double.parseDouble(probabilityField.getText()) > 1)
                    {
                        JOptionPane.showMessageDialog(jPanel, "Prawdopodobieństwo powinno być z przedziału 0-1 oraz być zapisane w notacji z kropką (np. 0.5)");

                    }
                    else if(Integer.parseInt(vertexField.getText()) > 120){
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120");

                    }
                    else
                App.displaySimpleGraph(Integer.parseInt(vertexField.getText()),Double.parseDouble(probabilityField.getText()));
                }
                catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120.\nPrawdopodobieństwo powinno być z przedziału 0 - 1 oraz być zapisane w notacji z kropką (np. 0.5)");
                }

            }
        });
        generateAndSearchCykleButton.setText("<html><center>"+"Generuj graf i wyszukaj"+"<br>"+"cykle trójelementowe"+"</center></html>");
        generateAndSearchCykleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if(Double.parseDouble(probabilityField.getText()) > 1)
                    {
                        JOptionPane.showMessageDialog(jPanel, "Prawdopodobieństwo powinno być z przedziału 0-1 oraz być zapisane w notacji z kropką (np. 0.5)");

                    }
                    else if(Integer.parseInt(vertexField.getText()) > 120){
                        JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120");

                    }
                        App.displayGraphWithCycles(Integer.parseInt(vertexField.getText()),Double.parseDouble(probabilityField.getText()), Long.parseLong(highlightingTime.getText()));
                }
                catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(jPanel, "Ilość wierzchołków powinna być liczbą całkowitą < 120.\nPrawdopodobieństwo powinno być z przedziału 0 - 1 oraz być zapisane w notacji z kropką (np. 0.5)\nDla wyświetlenia cykli grafu podaj czas podświetlania.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
