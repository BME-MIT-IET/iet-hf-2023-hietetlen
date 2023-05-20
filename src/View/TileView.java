package View;

import Model.Tiles.Tile;
import Model.Virologist;
import Utils.EventType;
import Utils.Observer;
import Utils.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TileView extends JPanel implements Observer<Tile> {

    Tile tile;

    private int nextTile = 0;
    private int selectedVirologist = 0;
    private Color selectedColor = new Color(70, 199, 87);
    private Color notSelectedColor = new Color(176, 176, 176);

    java.util.List<JButton> neighbourButtons;
    java.util.List<JButton> virologistButtons;

    JPanel neighbourPanel;
    JPanel virologistPanel;

    /**
     * Initializes a new view for the given tile.
     * @param tile The tile for which the view will be created
     */
    public TileView (Tile tile) {

        //setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        this.tile = tile;
        tile.subscribe(this, EventType.Any);

        //Virologist Picture and Name
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setMaximumSize(new Dimension(500, 200));
        JLabel picLabel = new JLabel(new ImageIcon(getTileImage()));
        imagePanel.add(picLabel, BorderLayout.CENTER);
        JLabel nameLabel = new JLabel(getTileName());
        Font f = nameLabel.getFont();
        nameLabel.setFont(new Font(f.getName(), f.getStyle(), 22));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0,28,0,0));
        imagePanel.add(nameLabel, BorderLayout.PAGE_END);
        add(imagePanel, BorderLayout.PAGE_START);

        //All Data
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
        dataPanel.add(Box.createRigidArea(new Dimension(0,50)));

        neighbourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        neighbourButtons = new ArrayList<>();
        neighbourPanel.add(new JLabel("Neighbours: "));
        neighbourPanel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
        dataPanel.add(neighbourPanel);

        virologistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        virologistButtons = new ArrayList<>();
        virologistPanel.add(new JLabel("Virologists: "));
        virologistPanel.add(Box.createRigidArea(new Dimension(2,10)));
        virologistPanel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
        dataPanel.add(virologistPanel);
        dataPanel.add(Box.createRigidArea(new Dimension(0,50)));

        add(dataPanel, BorderLayout.LINE_START);

        reloadView();
    }

    /**
     *
     * @return A string that represents the name of the given tile type
     */
    protected String getTileName () {
        return "Plain Tile";
    }

    /**
     *
     * @return The image file name that corresponds to the given tile type
     */
    protected String getTileImage () {
        return "tile.png";
    }

    public void activate() {
        tile.subscribe(this, EventType.Any);
        reloadView();
    }

    public void deactivate() {
        tile.unsubscribe(this, EventType.Any);
    }

    public Tile getNextTile () {
        return tile.getNeighbours().get(nextTile);
    }

    public Virologist getSelectedVirologist () {
        return tile.getVirologists().get(selectedVirologist);
    }

    @Override
    public void getNotified(Subject<Tile> subject, EventType eventType) {
        reloadView();
    }

    private void setColors () {
        for (int i = 0; i < neighbourButtons.size(); i++)
            neighbourButtons.get(i).setBackground(i == nextTile ? selectedColor : notSelectedColor);
        for (int i = 0; i < virologistButtons.size(); i++)
            virologistButtons.get(i).setBackground(i == selectedVirologist ? selectedColor : notSelectedColor);
        revalidate();
        repaint();
    }

    private void reloadView() {

        for (JButton b : neighbourButtons) neighbourPanel.remove(b);
        neighbourButtons.clear();
        for (int i = 0; i < tile.getNeighbours().size(); i++) {
            JButton b = new JButton(Integer.toString(i));
            int iValue = i;
            b.addActionListener(ae -> {
                nextTile = iValue;
                setColors();
            });
            neighbourButtons.add(b);
            neighbourPanel.add(b);
        }
        if (nextTile > neighbourButtons.size() - 1) nextTile = 0;

        for (JButton b : virologistButtons) virologistPanel.remove(b);
        virologistButtons.clear();
        for (int i = 0; i < tile.getVirologists().size(); i++) {
            JButton b = new JButton(Integer.toString(i));
            int iValue = i;
            b.addActionListener(ae -> {
                selectedVirologist = iValue;
                setColors();
            });
            virologistButtons.add(b);
            virologistPanel.add(b);
        }
        if (selectedVirologist > virologistButtons.size() - 1) selectedVirologist = 0;

        setColors();

        revalidate();
        repaint();
    }

}
