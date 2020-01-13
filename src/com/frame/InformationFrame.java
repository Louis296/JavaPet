package com.frame;

import com.action.Ready;
import com.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationFrame {
    public static Boolean infoflag=false;
    private JFrame frame;
    JPanel infoPanel;
    JPanel gifPanel;
    public void go(){
        infoflag=true;
        frame =new JFrame("火柴人信息");
        frame.setBounds(300,400,700,500);

        infoPanel=new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("姓名：火柴人"));
        infoPanel.add(new JLabel("性别：火柴人"));
        infoPanel.add(new JLabel("爱好：wxlwxlwxlwxlwxl"));
        infoPanel.add(new JLabel("最爱wxl啦！！！"));

        gifPanel=new JPanel();
        gifPanel.add(new Ready().go());

        frame.getContentPane().add(infoPanel,BorderLayout.CENTER);
        frame.getContentPane().add(gifPanel,BorderLayout.EAST);

        MainFrame.actionflag="Ready";
        MainFrame.mainFrame.setVisible(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                infoflag=false;
                MainFrame.action=1;
                MainFrame.mainFrame.setVisible(true);
            }
        });
        frame.setVisible(true);

    }

}
