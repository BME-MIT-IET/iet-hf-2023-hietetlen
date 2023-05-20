package View;

import Control.LevelManager;
import Control.RoundManager;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Model.Virologist;
import Utils.EventType;
import Utils.Observer;
import Utils.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class VirologistView extends JPanel implements Observer<Virologist> {
    private int viewId;

    String names[] = new String[]{"Dr. Clutter", "Dr. Dingus", "Dr. Grumble", "Dr. Rebuke", "Dr. Scuttle"};
    private JPanel pane;

    Virologist virologist;

    JButton interactButton;
    JButton moveButton;
    JButton stealButton;
    JComboBox<String> stealType;

    JPanel agentsPanel;
    java.util.HashMap<Agent, AgentView> agents;
    java.util.List<AgentView> agentViews;
    JPanel gcPanel;
    java.util.List<GeneticCodeView> gcViews;
    JPanel equipmentsPanel;
    java.util.List<EquipmentView> equipmentViews;
    JLabel substanceData;

    public String getVirologistName() {
        return names[viewId];
    }

    public VirologistView (Virologist virologist, int id) {
        viewId = id;
        setLayout(new BorderLayout());

        this.virologist = virologist;
        virologist.subscribe(this, EventType.Any);

        //Scroll View
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setPreferredSize(new Dimension(250, 500));
        add(scroll, BorderLayout.CENTER);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,10)));

        //Virologist Picture and Name
        JPanel virologistPanel = new JPanel(new BorderLayout());
        virologistPanel.setMaximumSize(new Dimension(500, 200));
        JLabel picLabel = new JLabel(new ImageIcon("virologist_main_" + id + ".png"));
        virologistPanel.add(picLabel, BorderLayout.CENTER);
        JLabel nameLabel = new JLabel(names[id]);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0,18,0,0));
        virologistPanel.add(nameLabel, BorderLayout.PAGE_END);
        pane.add(virologistPanel);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,25)));

        //Actions
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        interactButton = new JButton("Interact");
        interactButton.addActionListener(ae -> interact());
        actionsPanel.add(interactButton);
        moveButton = new JButton("Move");
        moveButton.addActionListener(ae -> move());
        actionsPanel.add(moveButton);
        pane.add(actionsPanel);

        //Stealing
        JPanel stealPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stealButton = new JButton("Steal");
        stealButton.addActionListener(ae -> steal());
        stealPanel.add(stealButton);
        stealType = new JComboBox<>();
        stealType.addItem("Equipment");
        stealType.addItem("Substance");
        stealPanel.add(stealType);
        pane.add(stealPanel);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,5)));

        //Substance
        JPanel substancePanel = new JPanel(new BorderLayout());
        JLabel substanceLabel = new JLabel("Substances");
        Font f = substanceLabel.getFont();
        substanceLabel.setFont(new Font(f.getName(), f.getStyle(), 22));
        substancePanel.add(substanceLabel, BorderLayout.PAGE_START);
        substanceData = new JLabel("Amino acid: 10 Nucleotide: 30");
        substancePanel.add(substanceData, BorderLayout.CENTER);
        pane.add(substancePanel);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,15)));

        //Agents
        JLabel agentLabel = new JLabel("Agents");
        agentLabel.setFont(new Font(f.getName(), f.getStyle(), 22));
        agentsPanel = new JPanel(new GridLayout(0,1));
        agentsPanel.add(agentLabel);
        agents = new HashMap<>();
        agentViews = new ArrayList<>();
        pane.add(agentsPanel);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,15)));

        //GeneticCodes
        JLabel gcLabel = new JLabel("Genetic codes");
        gcLabel.setFont(new Font(f.getName(), f.getStyle(), 22));
        gcPanel = new JPanel(new GridLayout(0,1));
        gcPanel.add(gcLabel);
        gcViews = new ArrayList<>();
        pane.add(gcPanel);

        //Padding
        pane.add(Box.createRigidArea(new Dimension(0,15)));

        //Equipments
        JLabel equipmentLabel = new JLabel("Equipments");
        equipmentLabel.setFont(new Font(f.getName(), f.getStyle(), 22));
        equipmentsPanel = new JPanel(new GridLayout(0,1));
        equipmentsPanel.add(equipmentLabel);
        equipmentViews = new ArrayList<>();
        pane.add(equipmentsPanel);

        reloadView();
    }

    @Override
    public void getNotified(Subject<Virologist> subject, EventType eventType) {
        reloadView();
    }

    public void addAgent(Agent a, AgentView av) {
        agents.put(a, av);
    }

    public void activate() {
        virologist.subscribe(this, EventType.Any);
        reloadView();
    }

    public void deactivate() {
        virologist.unsubscribe(this, EventType.Any);
    }

    //-----------------------------

    private  void reloadView() {
        //Agents
        for (AgentView av : agentViews) agentsPanel.remove(av);
        agentViews.clear();
        for (Agent a : virologist.getAgents()) {
            AgentView av = agents.get(a);
            agentViews.add(av);
            agentsPanel.add(av);
        }

        //Substances
        SubstanceContainer sc = virologist.getSubstanceContainer();
        substanceData.setText("Amino acid: " + sc.getAminoCount() + " Nucleotide: " + sc.getNucleotidCount());

        //GeneticCodes
        for (GeneticCodeView gcv : gcViews) gcPanel.remove(gcv);
        gcViews.clear();
        for (GeneticCode gc : virologist.getGeneticCodes()) {
            GeneticCodeView gcv = LevelManager.getInstance().getGeneticCodeView(gc);
            gcv.setParent(this);
            gcViews.add(gcv);
            gcPanel.add(gcv);
        }

        //Equipments
        for (EquipmentView ev : equipmentViews) equipmentsPanel.remove(ev);
        equipmentViews.clear();
        for (Equipment e : virologist.getEquipments()) {
            EquipmentView ev = LevelManager.getInstance().getEquipmentView(e);
            equipmentViews.add(ev);
            equipmentsPanel.add(ev);
        }

        revalidate();
        repaint();
    }

    private void move() {
        TileView tv = LevelManager.getInstance().getTileView(virologist.getCurrentTile());
        RoundManager.getInstance().move(tv.getNextTile());
    }

    private void interact() {
        RoundManager.getInstance().tileInteract();
    }

    private void steal() {
        TileView tv = LevelManager.getInstance().getTileView(virologist.getCurrentTile());
        boolean isEquipment = stealType.getSelectedItem().equals("Equipment");
        RoundManager.getInstance().steal(tv.getSelectedVirologist(), isEquipment);
    }
}
