package View;

import Control.LevelManager;
import Control.RoundManager;
import Model.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {

    private int playerCount;

    //UI
    JMenuItem menuItemAdd;
    JMenuItem menuItemRemove;
    RoundManagerView rmv;

    public GameFrame() {
        //Settings
        super("Blind Virologists");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setSize(new Dimension(800, 500));
        setMaximumSize(new Dimension(1500, 800));
        setIconImage(new ImageIcon("app-icon.png").getImage());
        playerCount = 4;

        //Contents
        setLayout(new BorderLayout());

        //Top Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Restart");
        menuItem.addActionListener(ae -> onRestart());
        menu.add(menuItem);
        menuItemAdd = new JMenuItem();
        menuItemAdd.addActionListener(ae -> changePlayerCount(1));
        menu.add(menuItemAdd);
        menuItemRemove = new JMenuItem();
        menuItemRemove.addActionListener(ae -> changePlayerCount(-1));
        menu.add(menuItemRemove);
        setJMenuBar(menuBar);
        changePlayerCount(0);

        //RoundManagerView
        onRestart();

        pack();
        setVisible(true);
    }

    private void changePlayerCount (int delta) {
        playerCount += delta;
        playerCount = Math.max(1, Math.min(5, playerCount));
        menuItemAdd.setText("Add Player (" + playerCount + ")");
        menuItemRemove.setText("Remove Player (" + playerCount + ")");
    }

    public void onRestart () {
        LevelManager.getInstance().createLevel("", playerCount);
        java.util.List<Virologist> vs = LevelManager.getInstance().getVirologists();
        RoundManager.getInstance().start(vs);
        if (rmv != null) {
            remove(rmv);
        }
        rmv = new RoundManagerView(this);
        add(rmv, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
