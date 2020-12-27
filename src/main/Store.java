package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import Items.*;
import Items.Decorator.*;
import Items.Decorator.Component;

public class Store extends JPanel {
    private Hashtable<String, Product> productList = new Hashtable<String, Product>();
    private Status user;
    private JLabel remainMoney;
    private JButton exitButton;
    private GameMap map;

    public Store(GameMap mp) {
        map = mp;
        Item levelA = new LevelA("A", 30);
        Item levelB = new LevelB("B", 20);
        Item levelC = new LevelC("C", 10);
        Component coffeeA = new Coffee(levelA, "coffee", 100);
        Component coffeeB = new Coffee(levelB, "coffee", 100);
        Component coffeeC = new Coffee(levelC, "coffee", 100);
        Component porkA = new Pork(levelA, "pork", 200);
        Component porkB = new Pork(levelB, "pork", 200);
        Component porkC = new Pork(levelC, "pork", 200);
        Component bombA = new Bomb(levelA, "bomb", 300);
        Component bombB = new Bomb(levelB, "bomb", 300);
        Component bombC = new Bomb(levelC, "bomb", 300);
        productList.put(coffeeA.getName(), new Product(coffeeA.getName(), coffeeA.getPrice(), coffeeA, this));
        productList.put(coffeeB.getName(), new Product(coffeeB.getName(), coffeeB.getPrice(), coffeeB, this));
        productList.put(coffeeC.getName(), new Product(coffeeC.getName(), coffeeC.getPrice(), coffeeC, this));

        productList.put(porkA.getName(), new Product(porkA.getName(), porkA.getPrice(), porkA, this));
        productList.put(porkB.getName(), new Product(porkB.getName(), porkB.getPrice(), porkB, this));
        productList.put(porkC.getName(), new Product(porkC.getName(), porkC.getPrice(), porkC, this));

        productList.put(bombA.getName(), new Product(bombA.getName(), bombA.getPrice(), bombA, this));
        productList.put(bombB.getName(), new Product(bombB.getName(), bombB.getPrice(), bombB, this));
        productList.put(bombC.getName(), new Product(bombC.getName(), bombC.getPrice(), bombC, this));

        remainMoney = new JLabel("0");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        formProperLayout();

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(false);
    }

    /**
     * This method should called when clicking the lunch buttom of store.
     * 
     * @user The status of user
     */
    public void init(Status user) {
        this.user = user;
        map.setVisible(false);
        productList.forEach((k, v) -> {
            v.reset();
        });
        remainMoney.setText(String.valueOf(user.getMoney()));
        setVisible(true);
        System.out.println("init");
    }

    /**
     * This method should be called when exiting the store.
     */
    public void exit() {
        setVisible(false);
        map.setVisible(true);
        map.requestFocusInWindow();
    }

    public void buy(Product p, Integer n, Component Item) {
        if (n * p.getPrice() > user.getMoney()) {
            JFrame warning = new JFrame();
            JLabel text = new JLabel("Not enough money!!");
            text.setFont(new Font(text.getFont().getFamily(), text.getFont().getStyle(), 30));
            warning.add(text);
            warning.setVisible(true);
            warning.pack();
            return;
        }
        user.updateMoney(-n * p.getPrice());
        remainMoney.setText(String.valueOf(user.getMoney()));
        if (user.skill.contains(p.getName()))
            user.skill.put(p.getName(), user.skill.get(p.getName()) + n);
        else {
            user.skill.put(p.getName(), n);
            user.skillContent.put(p.getName(), Item);
        }
    }

    private void formProperLayout() {
        JLabel l = new JLabel("Money:");

        JLabel img = new JLabel(new ImageIcon("./image/store/sale.gif"));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        /* Horizontal */
        GroupLayout.ParallelGroup hg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg1.addComponent(productList.get("coffeeA"));
        hg1.addComponent(productList.get("coffeeB"));
        hg1.addComponent(productList.get("coffeeC"));

        GroupLayout.ParallelGroup hg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg2.addComponent(productList.get("porkA"));
        hg2.addComponent(productList.get("porkB"));
        hg2.addComponent(productList.get("porkC"));

        GroupLayout.ParallelGroup hg3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg3.addComponent(productList.get("bombA"));
        hg3.addComponent(productList.get("bombB"));
        hg3.addComponent(productList.get("bombC"));

        GroupLayout.ParallelGroup hg5 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hg5.addGroup(layout.createSequentialGroup().addComponent(l).addComponent(remainMoney));
        hg5.addComponent(img);
        hg5.addComponent(exitButton);

        layout.setHorizontalGroup(
                layout.createSequentialGroup().addGroup(hg1).addGroup(hg2).addGroup(hg3).addGroup(hg5));

        /* Vertical */
        GroupLayout.ParallelGroup vg1 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg1.addComponent(productList.get("coffeeA"));
        vg1.addComponent(productList.get("porkA"));
        vg1.addComponent(productList.get("bombA"));
        vg1.addComponent(l);
        vg1.addComponent(remainMoney);

        GroupLayout.ParallelGroup vg2 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg2.addComponent(productList.get("coffeeB"));
        vg2.addComponent(productList.get("porkB"));
        vg2.addComponent(productList.get("bombB"));
        vg2.addComponent(img);

        GroupLayout.ParallelGroup vg3 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg3.addComponent(productList.get("coffeeC"));
        vg3.addComponent(productList.get("porkC"));
        vg3.addComponent(productList.get("bombC"));
        vg3.addComponent(exitButton);

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(vg1).addGroup(vg2).addGroup(vg3));
    }

}
