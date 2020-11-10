import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Store extends JPanel
{
    private Hashtable<String, Product> productList;
    private Status user;
    private JLabel remainMoney;
    private JButton exitButton;
    private GameMap2 map2;

    public Store(GameMap2 mp2)
    {
    	System.out.println(mp2.getClass());
    	map2 = mp2;
        productList = new Hashtable<String, Product>();
        productList.put("leg", new Product("leg", 50, this));
        productList.put("coffee", new Product("coffee", 50, this));
        productList.put("G_test", new Product("G_test", 50, this));

        productList.put("ticket", new Product("ticket", 1000, this));
        productList.put("redblue", new Product("redblue", 200, this));
        productList.put("underwear", new Product("underwear", 200, this));

        productList.put("medician", new Product("medician", 300, this));
        productList.put("phone", new Product("phone", 300, this));
        productList.put("mask", new Product("mask", 300, this));
        productList.put("H_test", new Product("H_test", 300, this));

        remainMoney = new JLabel("0");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                exit();
            }
        });

        formProperLayout();

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(false);
    }

    /**
     * This method should called when clicking the lunch
     * buttom of store.
     * @user The status of user
     */
    public void init(Status user)
    {
        this.user = user;
        map2.setVisible(false);
        productList.forEach((k, v) -> { v.reset(); });
        remainMoney.setText(String.valueOf(user.money));
        setVisible(true);
        System.out.println("init");
    }

    /**
     * This method should be called when exiting the store.
     */
    public void exit()
    {
        setVisible(false);
        map2.setVisible(true);
        map2.requestFocusInWindow();
    }

    public void buy(Product p, Integer n)
    {
        if (n * p.getPrice() > user.money) {
            JFrame warning = new JFrame();
            JLabel text = new JLabel("Not enough money!!");
            text.setFont(new Font(text.getFont().getFamily(), text.getFont().getStyle(), 30));
            warning.add(text);
            warning.setVisible(true);
            warning.pack();
            return;
        }

        user.money -= n * p.getPrice();
        remainMoney.setText(String.valueOf(user.money));
        if (user.skill.contains(p.getName()))
            user.skill.put(p.getName(), user.skill.get(p.getName()) + n);
        else
            user.skill.put(p.getName(), n);
    }

    private void formProperLayout()
    {
        JLabel l = new JLabel("Money:");
       
        JLabel img = new JLabel(new ImageIcon("image/store/sale.gif"));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        /* Horizontal */
        GroupLayout.ParallelGroup hg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg1.addComponent(productList.get("leg"));
        hg1.addComponent(productList.get("ticket"));
        hg1.addComponent(productList.get("phone"));

        GroupLayout.ParallelGroup hg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg2.addComponent(productList.get("coffee"));
        hg2.addComponent(productList.get("redblue"));
        hg2.addComponent(productList.get("medician"));

        GroupLayout.ParallelGroup hg3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg3.addComponent(productList.get("G_test"));
        hg3.addComponent(productList.get("underwear"));
        hg3.addComponent(productList.get("H_test"));

        GroupLayout.ParallelGroup hg4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg4.addComponent(productList.get("mask"));

        GroupLayout.ParallelGroup hg5 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hg5.addGroup(layout.createSequentialGroup().addComponent(l).addComponent(remainMoney));
        hg5.addComponent(img);
        hg5.addComponent(exitButton);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                                          .addGroup(hg1)
                                          .addGroup(hg2)
                                          .addGroup(hg3)
                                          .addGroup(hg4)
                                          .addGroup(hg5));

        /* Vertical */
        GroupLayout.ParallelGroup vg1 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg1.addComponent(productList.get("leg"));
        vg1.addComponent(productList.get("coffee"));
        vg1.addComponent(productList.get("G_test"));
        vg1.addComponent(l);
        vg1.addComponent(remainMoney);

        GroupLayout.ParallelGroup vg2 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg2.addComponent(productList.get("ticket"));
        vg2.addComponent(productList.get("redblue"));
        vg2.addComponent(productList.get("underwear"));
        vg2.addComponent(img);

        GroupLayout.ParallelGroup vg3 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg3.addComponent(productList.get("phone"));
        vg3.addComponent(productList.get("medician"));
        vg3.addComponent(productList.get("H_test"));
        vg3.addComponent(productList.get("mask"));
        vg3.addComponent(exitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup().addGroup(vg1).addGroup(vg2).addGroup(vg3));
    }

}
