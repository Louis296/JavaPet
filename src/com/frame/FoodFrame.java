package com.frame;

import com.others.Food;
import com.others.FoodList;
import com.others.Setting;
import com.others.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class FoodFrame {
    private JDialog frame;
    private FoodList<Food> foods;
    private JPanel selectPanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JList foodList;
    private JTextArea infoOfFood;
    private int selectedIndex=-1;

    public void go(JFrame fatherFrame, FoodList<Food> fs) {
        foods=fs;
        frame = new JDialog();
        frame.setBounds(new Rectangle(
                (int) fatherFrame.getBounds().getX()+50,
                (int) fatherFrame.getBounds().getY()+50,
                550,300
        ));
        frame.setTitle(foods.getName());
        frame.setResizable(false);

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
        JButton eat = new JButton("就吃它了！");
        JButton cancel=new JButton("算了还是换个食堂吧");
        buttonPanel=new JPanel();
        buttonPanel.add(eat);
        buttonPanel.add(cancel);
        contentPanel.add(infoOfFood);
        contentPanel.add(buttonPanel);

        eat.addActionListener(e-> {
            if (selectedIndex>=0) {
                Setting.age+=foods.get(selectedIndex).getGrowAge();
                JOptionPane.showMessageDialog(frame,"reui真难吃!\n(火柴人长大了"+foods.get(selectedIndex).getGrowAge()+"岁）");
            }
        });
        cancel.addActionListener(e->frame.setVisible(false));

        frame.getContentPane().add(selectPanel,BorderLayout.CENTER);
        frame.getContentPane().add(contentPanel,BorderLayout.EAST);
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setVisible(true);
    }

    class ListSelectionHandle implements ListSelectionListener{

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
