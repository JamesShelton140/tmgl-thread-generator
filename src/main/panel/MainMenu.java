package main.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main.data.DataStore;
import main.data.MatchFactory;
import main.data.types.Team;

public class MainMenu extends JPanel
{
    private final DataStore data;
    private final JPanel centreWrapper = new JPanel();
    private final MatchFactory matchFactory;

    public MainMenu(DataStore data)
    {
        this.data = data;

        matchFactory = new MatchFactory(data, this);

        this.setLayout(new BorderLayout());

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener((e -> {
            centreWrapper.removeAll();
            create();
            revalidate();
            repaint();
        }));

        this.add(homeButton, BorderLayout.NORTH);
        this.add(new JScrollPane(centreWrapper), BorderLayout.CENTER);

        create();
    }

    private void create()
    {
        JButton newMatchButton = new JButton("New Match");
        newMatchButton.addActionListener((e -> {
            matchFactory.newMatch();
        }));
        centreWrapper.add(newMatchButton);
    }

    public void showComponent(JComponent component)
    {
        centreWrapper.removeAll();
        centreWrapper.add(component);

        revalidate();
        repaint();
    }
}
