package com.frame;

import com.others.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RestaurantFrame {
    public static ArrayList<FoodList<Food>> restaurants;
    private JFrame frame;
    private JPanel buttonPanel;

    public void go(){
        MainFrame.haveOtherFrame=true;
        MainFrame.actionState= State.STOP;
        frame=new JFrame("去哪里吃呢？");
        frame.setBounds(300,400,300,250);

        setRestaurants();

        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("华农太难吃了我不想吃华农了.....");
        JMenuItem menuItem=new JMenuItem("去找zzw吃点好的！");
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, Setting.name+"：md好远哦不想动"));
        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        buttonPanel=new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
        buttonPanel.setLayout(new GridLayout(2,3,5,5));
        JButton taoYuan=new JButton(restaurants.get(0).getName());
        taoYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(0)));
        JButton huiYuan=new JButton(restaurants.get(1).getName());
        JButton mengZeYuan=new JButton(restaurants.get(2).getName());
        JButton boYuan=new JButton(restaurants.get(3).getName());
        JButton buXingJie=new JButton(restaurants.get(4).getName());
        JButton zhuYuan=new JButton(restaurants.get(5).getName());
        buttonPanel.add(taoYuan);
        buttonPanel.add(huiYuan);
        buttonPanel.add(mengZeYuan);
        buttonPanel.add(boYuan);
        buttonPanel.add(buXingJie);
        buttonPanel.add(zhuYuan);

        frame.getContentPane().add(buttonPanel,BorderLayout.CENTER);

        MainFrame.mainFrame.setVisible(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Tools.closeFrame(frame);
            }
        });

        frame.setVisible(true);

    }

    private void setRestaurants(){
        restaurants=new ArrayList<>();
        FoodList<Food> taoYuan=new FoodList<>("桃园");
        taoYuan.add(new Food("精品套餐","用散粒的大米辅以精心调制的大量食用油制作而成的佳肴，火柴人吃完就会吐（不是）",0.01));
        taoYuan.add(new Food("蛋包饭","传说是wxl的最爱，但因为wxl吃的时间太长感到厌倦而被抛弃，至今wxl仍会尝试",0.02));
        taoYuan.add(new Food("卤肉饭","听起来很正常的卤肉饭，但其实里面都是玉米，玉米的甜味与卤肉的香味巧妙融合，是不可多得的佳肴（reui）",0.01));
        FoodList<Food> huiYuan=new FoodList<>("荟园");
        FoodList<Food> mengZeYuan=new FoodList<>("梦泽园");
        FoodList<Food> boYuan=new FoodList<>("博园");
        FoodList<Food> buXingJie=new FoodList<>("步行街");
        FoodList<Food> zhuYuan=new FoodList<>("竹园");

        restaurants.add(taoYuan);
        restaurants.add(huiYuan);
        restaurants.add(mengZeYuan);
        restaurants.add(boYuan);
        restaurants.add(buXingJie);
        restaurants.add(zhuYuan);
    }

}
