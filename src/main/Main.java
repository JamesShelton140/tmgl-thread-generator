package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.panel.MainMenu;

public class Main {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("TMGL Thread Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        JPanel mainMenu = new MainMenu();
        frame.getContentPane().add(mainMenu);
        frame.setVisible(true);
    }
}
