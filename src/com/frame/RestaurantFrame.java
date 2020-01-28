package com.frame;

import com.others.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class RestaurantFrame {
    public static ArrayList<FoodList<Food>> restaurants;
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel HUSTPanel;
    private JMenuBar menuBar;
    public void go(){
        MainFrame.haveOtherFrame=true;
        MainFrame.actionState= State.STOP;
        frame=new JFrame("去哪里吃呢？");
        frame.setBounds(300,400,300,250);

        setRestaurants();

        menuBar=new JMenuBar();
        JMenu mainMenu=new JMenu("信息/求助");
        JMenuItem infoItem=new JMenuItem("食堂营业时间");
        infoItem.addActionListener(e->JOptionPane.showMessageDialog(frame,"早上 7:00-9:00\n中午 11:00-13:00\n晚上 18:00-20:00"));
        JMenu helpMenu=new JMenu("华农太难吃了我不想吃华农了.....");
        JMenuItem helpItem=new JMenuItem("去找zzw吃点好的！");
        helpItem.addActionListener(e -> {
            if (couldGoHUST()){

                HUSTPanel=new JPanel();
                HUSTPanel.setBorder(new EmptyBorder(5,5,5,5));
                HUSTPanel.setLayout(new GridLayout(2,3,5,5));
                JButton xiYi=new JButton(restaurants.get(6).getName());
                JButton baiJingYuan=new JButton(restaurants.get(7).getName());
                HUSTPanel.add(xiYi);
                HUSTPanel.add(baiJingYuan);

                menuBar.removeAll();
                menuBar.add(new JMenu("随便吃哦，zzw请客！"));
                frame.setTitle("到华科咯！");
                frame.remove(buttonPanel);
                frame.getContentPane().add(HUSTPanel,BorderLayout.CENTER);
                frame.validate();
            }else
                JOptionPane.showMessageDialog(frame, Setting.name+"：md好远哦不想动，下周再说吧");
        });
        helpMenu.add(helpItem);
        mainMenu.add(infoItem);
        mainMenu.add(helpMenu);
        menuBar.add(mainMenu);
        frame.setJMenuBar(menuBar);

        buttonPanel=new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
        buttonPanel.setLayout(new GridLayout(2,3,5,5));
        JButton taoYuan=new JButton(restaurants.get(0).getName());
        taoYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(0)));
        JButton huiYuan=new JButton(restaurants.get(1).getName());
        huiYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(1)));
        JButton mengZeYuan=new JButton(restaurants.get(2).getName());
        mengZeYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(2)));
        JButton boYuan=new JButton(restaurants.get(3).getName());
        boYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(3)));
        JButton buXingJie=new JButton(restaurants.get(4).getName());
        buXingJie.addActionListener(e->new FoodFrame().go(frame,restaurants.get(4)));
        JButton zhuYuan=new JButton(restaurants.get(5).getName());
        zhuYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(5)));
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
        huiYuan.add(new Food("水饺","虽然是只有一种馅的普通水饺，但一直是华农的招牌菜，维持着众多华农学子生存的最低保障。这种普通的饺子能引来长队的奇观只会在华农看到",0.02));
        huiYuan.add(new Food("贵贵套餐","这一套餐曾在华农红极一时，但出现了不久后味道就逐渐下降。在历次更迭中更是遵遁了华农食堂一贯“去其精华，取其糟粕”的传统，而且它真的名副其实的贵",0.01));
        huiYuan.add(new Food("麻辣烫","不怎么麻也不怎么烫，但是是一碗味道正常的麻辣烫（你还想期盼什么呢）",0.02));
        FoodList<Food> mengZeYuan=new FoodList<>("梦泽园");
        mengZeYuan.add(new Food("猪脚饭","华农食堂的绝对招牌，美味的猪脚肉配上可口的咸菜，虽然有时米饭会很黏，但瑕不掩瑜，作为wxl钦点的美味佳肴，绝对不可错过！",0.1));
        FoodList<Food> boYuan=new FoodList<>("博园");
        FoodList<Food> buXingJie=new FoodList<>("步行街");
        FoodList<Food> zhuYuan=new FoodList<>("小卖部");

        FoodList<Food> xiYi=new FoodList<>("西一");
        FoodList<Food> baiJingYuan=new FoodList<>("百景园");
        FoodList<Food> xiaoChiCheng=new FoodList<>("小吃城");
        FoodList<Food> jiJinYuan=new FoodList<>("集锦园");
        FoodList<Food> dongYi=new FoodList<>("东一");
        FoodList<Food> yunJiu=new FoodList<>("韵酒");
        FoodList<Food> dongJiaoGong=new FoodList<>("东教工");

        restaurants.add(taoYuan);
        restaurants.add(huiYuan);
        restaurants.add(mengZeYuan);
        restaurants.add(boYuan);
        restaurants.add(buXingJie);
        restaurants.add(zhuYuan);

        restaurants.add(xiYi);
        restaurants.add(baiJingYuan);
        restaurants.add(xiaoChiCheng);
        restaurants.add(jiJinYuan);
        restaurants.add(dongYi);
        restaurants.add(yunJiu);
        restaurants.add(dongJiaoGong);
    }

    private boolean couldGoHUST(){
        boolean couldGo;
        Calendar calendar=Calendar.getInstance();
        if (calendar.get(Calendar.DATE)-Setting.lastGoHUSTCalendar.get(Calendar.DATE)>=7)
            couldGo=true;
        else
            couldGo=false;
        Setting.lastGoHUSTCalendar=calendar;
        return couldGo;
    }
}
