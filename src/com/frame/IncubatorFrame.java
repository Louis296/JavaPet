package com.frame;

import com.action.Egg;
import com.others.State;
import com.others.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IncubatorFrame {
    private JFrame frame;
    private JPanel contentPanel;
    private Boolean isWorking=false;
    public void go(){
        MainFrame.haveOtherFrame=true;
        frame=new JFrame("孵化器");
        frame.setBounds(300,400,300,200);
        frame.setResizable(false);

        contentPanel=new JPanel();
        contentPanel.setBackground(Color.gray);
        MainFrame.actionState= State.EGG;
        JLabel egg=new Egg().go();
        egg.setLocation(300,100);
        contentPanel.add(egg);

        JButton startOrStop=new JButton("开始孵化");
        startOrStop.addActionListener(e->{
            if (!isWorking){
                isWorking=true;
                startOrStop.setText("暂停孵化");
                contentPanel.setBackground(Color.pink);
            }
            else{
                isWorking=false;
                startOrStop.setText("开始孵化");
                contentPanel.setBackground(Color.gray);
            }
        });

        frame.getContentPane().add(contentPanel,BorderLayout.CENTER);
        frame.getContentPane().add(startOrStop,BorderLayout.SOUTH);

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
