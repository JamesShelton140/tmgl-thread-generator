package main.panel.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import main.data.types.Round;

public class RoundResultPanel extends JPanel
{
    private final List<String> playerList;
    private JList<String> list;
    private List<String> blueTeam;

    public RoundResultPanel(String title, List<String> playerList, Round round, ActionListener actionListener)
    {
        this.playerList = playerList;
        this.blueTeam = Arrays.asList(round.getBlueTeam().getPlayer1(), round.getBlueTeam().getPlayer2());

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String player : playerList) {
            model.addElement(player);
        }
        list = new JList<>(model);
        MouseAdapter listener = new ReorderListener(list);
        list.addMouseListener(listener);
        list.addMouseMotionListener(listener);
        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String)
                {
                    if (blueTeam.contains(value))
                    {
                        setBackground(new Color(89, 153, 255, 150));
                    } else {
                        setBackground(new Color(255, 89, 89, 150));
                    }
                    if (isSelected) {
                        setBackground(getBackground().darker());
                    }
                }
                return c;
            }
        });

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
            HashMap<String, Integer> result = new HashMap<>();
            for(int i = 0; i < 4; i++)
            {
                result.put(list.getModel().getElementAt(i), i+1);
            }
            round.setResult(result);

            actionListener.actionPerformed(e);
        });

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(list, BorderLayout.CENTER);
        this.add(doneButton, BorderLayout.SOUTH);
    }

    class ReorderListener extends MouseAdapter {

        private JList<String> list;
        private int pressIndex = 0;
        private int releaseIndex = 0;

        public ReorderListener(JList<String> list) {
            if (!(list.getModel() instanceof DefaultListModel)) {
                throw new IllegalArgumentException("List must have a DefaultListModel");
            }
            this.list = list;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            pressIndex = list.locationToIndex(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            releaseIndex = list.locationToIndex(e.getPoint());
            if (releaseIndex != pressIndex && releaseIndex != -1) {
                reorder();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseReleased(e);
            pressIndex = releaseIndex;
        }

        private void reorder() {
            DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
            String dragee = model.elementAt(pressIndex);
            model.removeElementAt(pressIndex);
            model.insertElementAt(dragee, releaseIndex);
        }
    }
}
