package View;

import Control.RoundManager;
import Model.EffectHolders.Agent;
import Model.GeneticCode;
import Utils.EventType;
import Utils.Observer;
import Utils.Subject;

import javax.swing.*;
import java.awt.*;

public abstract class GeneticCodeView extends JPanel implements Observer<GeneticCode> {

    protected VirologistView parent;
    protected GeneticCode gc;

    /**
     * Initializes a new view for the given genetic code.
     * @param gc The genetic code for which the view will be created
     */
    public GeneticCodeView(GeneticCode gc) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        this.gc = gc;
        gc.subscribe(this, EventType.Any);

        JPanel texts = new JPanel();
        texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));
        texts.add(new JLabel(getGCName()), BorderLayout.PAGE_START);
        texts.add(Box.createRigidArea(new Dimension(0,10)));
        texts.add(new JLabel("Cost: " + gc.getCost().getAminoCount() + "A, " + gc.getCost().getNucleotidCount() + "N"), BorderLayout.PAGE_END);
        add(texts, BorderLayout.LINE_START);

        add(Box.createRigidArea(new Dimension(25,0)), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton virusButton = new JButton("Virus");
        virusButton.addActionListener(ae -> makeVirus());
        virusButton.setPreferredSize(new Dimension(80, virusButton.getPreferredSize().height));
        buttonPanel.add(virusButton, BorderLayout.PAGE_START);
        JButton vaccineButton = new JButton("Vaccine");
        vaccineButton.addActionListener(ae -> makeVaccine());
        vaccineButton.setPreferredSize(new Dimension(80, vaccineButton.getPreferredSize().height));
        buttonPanel.add(vaccineButton, BorderLayout.PAGE_END);
        add(buttonPanel, BorderLayout.LINE_END);

        setPreferredSize(new Dimension(getPreferredSize().width , 50));
    }

    public void setParent(VirologistView newParent) {
        parent = newParent;
    }

    /**
     *
     * @return A string that represents the name of the given genetic code
     */
    abstract protected String getGCName();

    /**
     * Instantiates and gives a new virus for the active virologist if possible.
     */
    protected void makeVirus() {
        RoundManager.getInstance().makeAgent(true, gc);
    }

    /**
     * Instantiates and gives a new vaccine for the active virologist if possible.
     */
    protected void makeVaccine() {
        RoundManager.getInstance().makeAgent(false, gc);
    }

    /**
     * With this function call, the class will be notified whenever a virologist attempts to create an agent.
     * @param subject
     * @param eventType
     */
    @Override
    public void getNotified(Subject<GeneticCode> subject, EventType eventType) {
        Agent a = gc.getLast();
        parent.addAgent(a, new AgentView(a, getGCName()));
    }
}
