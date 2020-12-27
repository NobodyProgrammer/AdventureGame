package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Items.Decorator.*;
import Items.Decorator.Component;

public class Product extends JPanel {
    private JLabel name, price;
    private JButton buy;
    private JSpinner amount;
    private Store container;
    private Component content;

    public Product(String n, Integer p, Component con, Store s) {
        name = new JLabel(n);
        price = new JLabel(p.toString());
        amount = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
        amount.setMaximumSize(new Dimension(100, 25));
        this.content = con;
        container = s;

        buy = new JButton("Buy");
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buy((Integer) amount.getValue());
            }
        });

        formProperLayout();
    }

    public String getName() {
        return name.getText();
    }

    public Integer getPrice() {
        return Integer.parseInt(price.getText());
    }

    public void reset() {
        amount.setValue(0);
    }

    private void formProperLayout() {
        JLabel l1 = new JLabel("Price:");
        JLabel l2 = new JLabel("Amount:");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        /* Horizontal */
        GroupLayout.ParallelGroup hg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg1.addComponent(name);
        hg1.addComponent(l2);

        GroupLayout.ParallelGroup hg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup hg2a = layout.createSequentialGroup();
        hg2a.addComponent(l1);
        hg2a.addComponent(price);
        hg2.addGroup(hg2a);
        hg2.addComponent(amount);

        GroupLayout.ParallelGroup hg3 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hg3.addComponent(buy);

        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(hg1).addGroup(hg2).addGroup(hg3));

        /* Vertical */
        GroupLayout.ParallelGroup vg1 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg1.addComponent(name);
        vg1.addComponent(l1);
        vg1.addComponent(price);

        GroupLayout.ParallelGroup vg2 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg2.addComponent(l2);
        vg2.addComponent(amount);
        vg2.addComponent(buy);

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(vg1).addGroup(vg2));

        setVisible(true);
    }

    private void buy(Integer n) {
        container.buy(this, n, this.content);
    }

}
