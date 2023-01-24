package main.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import main.data.types.Team;

public class TeamPanel extends JPanel
{
    private final Team team;

    public TeamPanel(Team team)
    {
        this.team = team;

        this.setLayout(new BorderLayout());
        this.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));

        JLabel textBox = new JLabel();
        String openingTags = "<html><body style = 'padding: 5px'>";
        String closingTags = "</html><body>";
        String string = "<p style=\"font-size: large\">" + team.getName() + "</p>" +
                "<p>" + team.getPlayer1() + " &amp; " + team.getPlayer2() + "</p>" +
                "<p>Team Cast [" + team.getCastLanguage() + "]:&nbsp;" + team.getCastLink() + "</p>";
        textBox.setText(openingTags + string + closingTags);

        this.add(textBox, BorderLayout.CENTER);
    }
}
