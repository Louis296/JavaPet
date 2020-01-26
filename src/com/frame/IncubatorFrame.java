package com.frame;

import com.action.Egg;
import com.others.Setting;
import com.others.State;
import com.others.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IncubatorFrame {
    private JFrame frame;
    private JProgressBar progressBar;
    private JPanel contentPanel;
    private Boolean isWorking=false;
    public void go(){
        MainFrame.haveOtherFrame=true;
        frame=new JFrame("孵化器");
        frame.setBounds(300,400,300,200);
        frame.setResizable(false);

        progressBar=new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(Setting.incubatePresent);

        contentPanel=new JPanel();
        contentPanel.setBackground(Color.gray);
        MainFrame.actionState= State.EGG;
        contentPanel.add(new Egg().go(105,15));

        JButton startOrStop=new JButton("开始孵化");
        startOrStop.addActionListener(e->{
            if (!isWorking){
                isWorking=true;
                new Thread(new workingProgress()).start();
                startOrStop.setText("暂停孵化");
                frame.setTitle("孵化中......");
                contentPanel.setBackground(Color.pink);
            }
            else{
                isWorking=false;
                startOrStop.setText("开始孵化");
                frame.setTitle("孵化器");
                contentPanel.setBackground(Color.gray);
            }
        });

        frame.getContentPane().add(progressBar,BorderLayout.NORTH);
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

    class workingProgress implements Runnable{

        @Override
        public void run() {

            try{
                while(Setting.incubatePresent<100&&isWorking){
                    for (int i=0;i<3;i++){
                        Thread.sleep(10);
                        if (!isWorking)
                            break;
                    }
                    if (!isWorking)
                        break;
                    Setting.incubatePresent++;
                    progressBar.setValue(Setting.incubatePresent);
                }
                if (Setting.incubatePresent==100){
                    JOptionPane.showMessageDialog(frame,"孵化成功！！");
                    Setting.age+=1;
                    Tools.closeFrame(frame);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
