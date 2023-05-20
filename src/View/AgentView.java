package View;

import Control.LevelManager;
import Control.RoundManager;
import Model.EffectHolders.Agent;

import javax.swing.*;
import java.awt.*;

public class AgentView extends JPanel {

    Agent agent;

    /**
     * Initializes a new view for the given agent.
     * @param agent The agent for which the view will be created
     * @param agentName The name of the agent for which the view will be created
     */
    public AgentView(Agent agent, String agentName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        this.agent = agent;

        JPanel texts = new JPanel();
        texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));
        texts.add(new JLabel(agentName), BorderLayout.PAGE_START);
        texts.add(Box.createRigidArea(new Dimension(0,10)));
        texts.add(new JLabel(this.agent.isVaccine() ? "Vaccine" : "Virus"), BorderLayout.PAGE_END);
        add(texts, BorderLayout.LINE_START);

        add(Box.createRigidArea(new Dimension(25,0)), BorderLayout.CENTER);

        JButton useButton = new JButton("Use");
        useButton.addActionListener(ae -> {
            TileView tv = LevelManager.getInstance().getTileView(RoundManager.getInstance().getCurrentVirologist().getCurrentTile());
            RoundManager.getInstance().useAgent(tv.getSelectedVirologist(), this.agent);
        });
        useButton.setPreferredSize(new Dimension(80, useButton.getPreferredSize().height));
        add(useButton, BorderLayout.LINE_END);

        setPreferredSize(new Dimension(getPreferredSize().width , 50));
    }

}
