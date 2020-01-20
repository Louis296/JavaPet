package com.frame;

import com.action.Ready;
import com.others.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationFrame {
    public static Boolean infoflag=false;
    private JFrame frame;
    private JPanel infoPanel;
    private JPanel gifPanel;
    private JPanel buttonPanel;

    public void go(){
        infoflag=true;
        frame =new JFrame("火柴人信息");
        frame.setBounds(300,400,300,200);

        infoPanel=new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("  姓名： "+ Setting.name));
        infoPanel.add(new JLabel("  性别： "+Setting.sex));
        infoPanel.add(new JLabel("  爱好： "+Setting.hobby));
        infoPanel.add(new JLabel("  最爱wxl啦！！！"));

        gifPanel=new JPanel();
        gifPanel.setBackground(Color.gray);
        gifPanel.add(new Ready().go());

        buttonPanel=new JPanel();
        JButton confirmButton=new JButton("确定");
        JButton advanceButton=new JButton("高级");
        JButton cancelButton=new JButton("取消");
        buttonPanel.add(confirmButton);
        buttonPanel.add(advanceButton);
        buttonPanel.add(cancelButton);
        confirmButton.addActionListener(e->closeFrame());
        advanceButton.addActionListener(e->new OptionFrame().go(frame));
        cancelButton.addActionListener(e->closeFrame());

        frame.getContentPane().add(infoPanel,BorderLayout.CENTER);
        frame.getContentPane().add(gifPanel,BorderLayout.EAST);
        frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

        MainFrame.actionflag="Ready";
        MainFrame.mainFrame.setVisible(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame();
            }
        });
        frame.setVisible(true);
    }

    public void closeFrame(){
        frame.setVisible(false);
        infoflag=false;
        MainFrame.action=0;
        MainFrame.mainFrame.setVisible(true);

    }


}
