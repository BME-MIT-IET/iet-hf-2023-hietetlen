package View;

import Control.LevelManager;
import Control.RoundManager;
import Model.Tiles.Tile;
import Model.Virologist;
import Utils.EventType;
import Utils.Observer;
import Utils.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class RoundManagerView extends JPanel implements Observer<RoundManager> {
    private GameFrame frame;

    private class VirologistBox extends JPanel {
        private Virologist virologist;
        private boolean active;
        public VirologistBox(Virologist virologist, int id, boolean defaultState) {
            this.virologist = virologist;
            setLayout(new BorderLayout());
            setActive(defaultState);
            ImageIcon ii = new ImageIcon("virologist_icon_" + id + ".png");
            JLabel picLabel = new JLabel(ii);
            add(picLabel, BorderLayout.CENTER);
        }
        public void setActive(boolean state) {
            active = state;
            setBorder(BorderFactory.createLineBorder(active ? Color.red : Color.gray, 3));
        }
        public Virologist getVirologist() { return  virologist; }
    }

    private Color completed = new Color(124, 191, 73);
    private Color notCompleted = new Color(194, 66, 52);
    private java.util.List<VirologistBox> virologistBoxes = new ArrayList<>();
    private TileView tileView;
    private VirologistView virologistView;

    //Swing components
    private JPanel boxContainer;
    private JPanel bottomPanel;
    private JLabel moveStatusLabel;
    private JLabel actionStatusLabel;

    /**
     * With this function call, the class will be notified whenever a virologist finishes their round.
     * @param subject
     * @param eventType
     */
    @Override
    public void getNotified(Subject<RoundManager> subject, EventType eventType) {
        switch (eventType) {
            case ActionCompleted: updateAction(); break;
            case MoveCompleted: updateMove(); break;
            case RoundInit: loadNextRound(); break;
            case GameWon:
                JOptionPane.showMessageDialog(frame, virologistView.getVirologistName() + "has won the game");
                frame.onRestart();
                break;
            default: break;
        }
    }


    public RoundManagerView (GameFrame f) {
        frame = f;

        //Setup
        setLayout(new BorderLayout());

        RoundManager.getInstance().subscribe(this, EventType.ActionCompleted);
        RoundManager.getInstance().subscribe(this, EventType.MoveCompleted);
        RoundManager.getInstance().subscribe(this, EventType.RoundInit);

        JPanel tl = new JPanel(new BorderLayout());
        boxContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        java.util.List<Virologist> vs = RoundManager.getInstance().getVirologists();
        for (int i = 0; i < vs.size(); i++) {
            VirologistBox vb = new VirologistBox(vs.get(i), i, false);
            virologistBoxes.add(vb);
            boxContainer.add(vb);
        }
        tl.add(boxContainer, BorderLayout.LINE_START);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        moveStatusLabel = new JLabel("ASDADASADSDSAADSDS");
        statusPanel.add(moveStatusLabel, BorderLayout.PAGE_START);
        actionStatusLabel = new JLabel("ASDDSADSDS");
        statusPanel.add(actionStatusLabel, BorderLayout.PAGE_END);
        tl.add(statusPanel, BorderLayout.CENTER);

        JPanel roundCompleteContainer = new JPanel(new BorderLayout());
        roundCompleteContainer.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        JButton roundCompleteButton = new JButton("ROUND COMPLETED");
        roundCompleteButton.setPreferredSize(new Dimension(238, 62));
        roundCompleteButton.addActionListener(ae -> RoundManager.getInstance().roundCompleted());
        roundCompleteContainer.add(roundCompleteButton, BorderLayout.CENTER);
        tl.add(roundCompleteContainer, BorderLayout.LINE_END);
        add(tl, BorderLayout.PAGE_START);

        //Bottom Game View
        bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.CENTER);
        loadNextRound();
    }

    private void updateMove () {
        moveStatusLabel.setText("Move completed");
        moveStatusLabel.setForeground(completed);
        loadTile(RoundManager.getInstance().getCurrentVirologist());
        revalidate();
        repaint();
    }

    private  void updateAction () {
        actionStatusLabel.setText("Action completed");
        actionStatusLabel.setForeground(completed);
        revalidate();
        repaint();
    }

    private void loadNextRound () {
        Virologist v = RoundManager.getInstance().getCurrentVirologist();

        loadTile(v);

        if (virologistView != null) {
            virologistView.deactivate();
            bottomPanel.remove(virologistView);
        }
        revalidate();
        repaint();
        virologistView = LevelManager.getInstance().getVirologistView(v);
        virologistView.activate();
        bottomPanel.add(virologistView, BorderLayout.LINE_END);


        for (VirologistBox vb : virologistBoxes) {
            vb.setActive(vb.getVirologist() == v);
        }

        moveStatusLabel.setText("Move not completed");
        moveStatusLabel.setForeground(notCompleted);
        actionStatusLabel.setText("Action not completed");
        actionStatusLabel.setForeground(notCompleted);
        revalidate();
        repaint();
    }

    private void loadTile (Virologist v) {
        Tile t = v.getCurrentTile();
        if (tileView != null) {
            tileView.deactivate();
            bottomPanel.remove(tileView);
        }
        tileView = LevelManager.getInstance().getTileView(t);
        tileView.activate();
        bottomPanel.add(tileView, BorderLayout.CENTER);
    }

    private void disableView() {
        RoundManager.getInstance().unsubscribe(this, EventType.ActionCompleted);
        RoundManager.getInstance().unsubscribe(this, EventType.MoveCompleted);
        RoundManager.getInstance().unsubscribe(this, EventType.RoundInit);
    }

}
