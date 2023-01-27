package main.panel.components;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ButtonScrollList extends JPanel
{
    private final List<String> itemList;
    private final JPanel viewPanel = new JPanel();
    private final JLabel titleLabel = new JLabel();

    public ButtonScrollList(String title, List<String> itemList, ActionListener listener)
    {
        this.itemList = itemList;

        this.setLayout(new BorderLayout());

        titleLabel.setText(title);

        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));

        for(String str : itemList)
        {
            JButton button = new JButton(str);
            button.addActionListener(listener);
            viewPanel.add(button);
        }

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(new JScrollPane(viewPanel), BorderLayout.CENTER);
    }
}
