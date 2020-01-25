package com.frame;

import com.action.Baby;
import com.action.Egg;
import com.action.Ready;
import com.others.Setting;
import com.others.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationFrame {
    private JFrame frame;
    private JPanel infoPanel;
    private JPanel gifPanel;
    private JPanel buttonPanel;

    public void go(){
        MainFrame.haveOtherFrame=true;
        frame =new JFrame("火柴人信息");
        frame.setBounds(300,400,300,200);
        frame.setResizable(false);

        infoPanel=new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("  姓名： "+ Setting.name));
        infoPanel.add(new JLabel("  性别： "+Setting.sex));
        infoPanel.add(new JLabel("  年龄： "+Setting.age));
        infoPanel.add(new JLabel("  爱好： "+Setting.hobby));
        infoPanel.add(new JLabel("\n\n\n  最爱wxl啦！！！"));

        gifPanel=new JPanel();
        gifPanel.setBackground(Color.gray);
        if (Setting.getAge()==-1) {
            gifPanel.add(new Egg().go());
            MainFrame.actionState= State.EGG;
        }
        if (Setting.getAge()>=0&&Setting.getAge()<=5) {
            gifPanel.add(new Baby().go());
            MainFrame.actionState= State.BABY;
        }
        if (Setting.getAge()>=5) {
            gifPanel.add(new Ready().go());
            MainFrame.actionState= State.READY;
        }

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
        MainFrame.action=0;
        MainFrame.haveOtherFrame=false;
        MainFrame.mainFrame.setVisible(true);
    }


}
