package com.frame;

import com.others.Setting;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FoodFrame {
    private JFrame frame;
    private JPanel buttonPanel;

    public void go(){
        MainFrame.haveOtherFrame=true;
        frame=new JFrame("火柴人餐厅");
        frame.setBounds(300,400,300,200);
//        frame.setResizable(false);

        buttonPanel=new JPanel();
        JButton rouSong=new JButton("肉松");
        buttonPanel.add(rouSong);

        rouSong.addActionListener(e->Setting.age++);

        frame.getContentPane().add(buttonPanel);

        MainFrame.mainFrame.setVisible(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame();
            }
        });
        frame.setVisible(true);

    }

    private void closeFrame(){
        frame.setVisible(false);
        MainFrame.haveOtherFrame=false;
        MainFrame.action=0;
        MainFrame.mainFrame.setVisible(true);

    }

}
