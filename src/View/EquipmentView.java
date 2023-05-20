package View;

import Model.EffectHolders.Equipment;

import javax.swing.*;
import java.awt.*;

public abstract class EquipmentView extends JPanel {

    protected  Equipment equipment;
    protected JLabel extraLabel;
    protected JButton useButton;

    /**
     * Initializes a new view for the given equipment.
     * @param equipment The equipment for which the view will be created
     */
    public EquipmentView (Equipment equipment) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        this.equipment = equipment;

        JPanel texts = new JPanel();
        texts.setLayout(new BoxLayout(texts, BoxLayout.PAGE_AXIS));
        texts.add(new JLabel(getEquipmentName()), BorderLayout.PAGE_START);
        texts.add(Box.createRigidArea(new Dimension(0,10)));
        extraLabel = new JLabel();
        texts.add(extraLabel, BorderLayout.PAGE_END);
        add(texts, BorderLayout.LINE_START);

        add(Box.createRigidArea(new Dimension(25,0)), BorderLayout.CENTER);

        useButton = new JButton("Use");
        useButton.setPreferredSize(new Dimension(80, useButton.getPreferredSize().height));
        useButton.addActionListener(ae -> use());
        add(useButton, BorderLayout.LINE_END);

        setPreferredSize(new Dimension(getPreferredSize().width , 50));
    }

    /**
     *
     * @return A string that represents the name of the given equipment
     */
    abstract protected String getEquipmentName();

    /**
     * With this function call, the current virologist can perform an action with the given equipment.
     */
    abstract protected void use();

}
