package com.frame;

import com.action.Baby;
import com.action.Egg;
import com.action.Ready;
import com.others.Setting;
import com.others.State;
import com.others.Tools;

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
        if (MainFrame.haveOtherFrame){
            JOptionPane.showMessageDialog(null,Setting.name+"：麻麻我还被挂在鼠标上呢！");
        }
        else{

            MainFrame.haveOtherFrame=true;
            frame =new JFrame("火柴人信息");
            frame.setBounds(300,400,300,200);
            frame.setResizable(false);

            infoPanel=new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(new JLabel("  姓名： "+ Setting.name));
            infoPanel.add(new JLabel("  性别： "+Setting.sex));
            infoPanel.add(new JLabel("  年龄： "+Setting.age));
            infoPanel.add(new JLabel("  爱好： "+Setting.hobby));
            infoPanel.add(Box.createVerticalStrut(45));
            infoPanel.add(new JLabel("  最爱wxl啦！！！"));

            gifPanel=new JPanel();
            gifPanel.setBackground(Color.gray);
            if (Setting.getAge()==-1) {
                MainFrame.actionState= State.EGG;
                gifPanel.add(new Egg().go());
            }
            if (Setting.getAge()>=0&&Setting.getAge()<5) {
                MainFrame.actionState= State.BABY;
                gifPanel.add(new Baby().go());
            }
            if (Setting.getAge()>=5) {
                MainFrame.actionState= State.READY;
                gifPanel.add(new Ready().go());
            }

            buttonPanel=new JPanel();
            JButton confirmButton=new JButton("确定");
            JButton advanceButton=new JButton("高级");
            JButton cancelButton=new JButton("取消");
            buttonPanel.add(confirmButton);
            buttonPanel.add(advanceButton);
            buttonPanel.add(cancelButton);
            confirmButton.addActionListener(e->Tools.closeFrame(frame));
            advanceButton.addActionListener(e->new OptionFrame().go(frame));
            cancelButton.addActionListener(e->Tools.closeFrame(frame));

            frame.getContentPane().add(infoPanel,BorderLayout.CENTER);
            frame.getContentPane().add(gifPanel,BorderLayout.EAST);
            frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

            MainFrame.mainFrame.setVisible(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Tools.closeFrame(frame);
                }
            });
            frame.setVisible(true);
        }
    }

}
