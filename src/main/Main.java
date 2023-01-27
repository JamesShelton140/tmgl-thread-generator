package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.data.DataStore;
import main.panel.MainMenu;

public class Main
{
    public static void main(String[] args)
    {
        DataStore data = new DataStore();

        JFrame frame = new JFrame("TMGL Thread Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        JPanel mainMenu = new MainMenu(data);
        frame.getContentPane().add(mainMenu);
        frame.setVisible(true);
    }
}
