package com.frame;

import com.others.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BabyFoodFrame {
    private JFrame frame;
    private FoodList<Food> foods;
    private JPanel selectPanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JList foodList;
    private JTextArea infoOfFood;
    private int selectedIndex=-1;

    public void go(){
        MainFrame.haveOtherFrame=true;
        MainFrame.actionState= State.STOP;
        frame=new JFrame("婴儿餐厅");
        frame.setBounds(300,400,550,300);
        frame.setResizable(false);

        setBabyFoodList();

        foodList=new JList();
        foodList.setListData(Tools.getListNameFrom(foods));

        selectPanel = new JPanel();
        selectPanel.setBorder(new EmptyBorder(5,5,5,5));
        selectPanel.setLayout(new BorderLayout(0,0));
        foodList.getSelectionModel().addListSelectionListener(new ListSelectionHandle());
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(foodList);
        selectPanel.add(scrollPane,BorderLayout.CENTER);

        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
        infoOfFood= new JTextArea();
        infoOfFood.setLineWrap(true);
        JButton eat = new JButton("就喂它吃这个！");
        JButton cancel=new JButton("算了还是不喂它了");
        buttonPanel=new JPanel();
        buttonPanel.add(eat);
        buttonPanel.add(cancel);
        contentPanel.add(infoOfFood);
        contentPanel.add(buttonPanel);

        eat.addActionListener(e-> {
            if (selectedIndex>=0) {
                if (Tools.couldEat()){
                    Setting.age+=foods.get(selectedIndex).getGrowAge();
                    JOptionPane.showMessageDialog(frame,Setting.name+"：reui真难吃!\n(火柴人长大了"+foods.get(selectedIndex).getGrowAge()+"岁）");
                }
                else {
                    JOptionPane.showMessageDialog(frame,Setting.name+"吃不下了!");
                }
            }
        });
        cancel.addActionListener(e->Tools.closeFrame(frame));

        frame.getContentPane().add(selectPanel,BorderLayout.CENTER);
        frame.getContentPane().add(contentPanel,BorderLayout.EAST);

        MainFrame.mainFrame.setVisible(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Tools.closeFrame(frame);
            }
        });
        frame.setVisible(true);
    }

    private void setBabyFoodList(){
        FoodList<Food> fs=new FoodList<>("婴儿食品");
        fs.add(new Food("肉松","礼拜四：麻麻麻麻我要吃肉松嘛！！！\n  zzw:醒醒礼拜四这个程序里面没有你！这是火柴人的特供肉松！！",0.01));
        fs.add(new Food("黄油","一种热量非常高的食物，它的特点是喜欢穿粉色的衣服（不是）",0.02));
        fs.add(new Food("牛奶","一瓶平凡的牛奶，wxl跑步后经常会买一杯",0.01));
        foods=fs;
    }

    class ListSelectionHandle implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm=(ListSelectionModel)e.getSource();
            selectedIndex=lsm.getLeadSelectionIndex();

            if (selectedIndex>=0){
                infoOfFood.setText("描述：\n    "+foods.get(selectedIndex).getDescription()+"\n");
                infoOfFood.append("\n效果：\n    "+"使火柴人长大"+foods.get(selectedIndex).getGrowAge()+"岁");
            }
        }
    }
}
