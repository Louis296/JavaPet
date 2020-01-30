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
        frame=new JFrame("孵化温室");
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
                frame.setTitle("孵化温室");
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
                        Thread.sleep(1000);
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
                    Setting.name=JOptionPane.showInputDialog(frame,
                            "幼小的火柴人睁开了眼，他出生的第一眼就看向了可爱的wxl，并把可爱的wxl认作了他的麻麻\n给幼小的火柴人取个名字吧！");
                    while (Setting.name==null||Setting.name.equals("")){
                        JOptionPane.showMessageDialog(frame,"名字不能为空！！！","错误",JOptionPane.ERROR_MESSAGE);
                        Setting.name=JOptionPane.showInputDialog(frame,
                                "幼小的火柴人睁开了眼，他出生的第一眼就看向了可爱的wxl，并把可爱的wxl认作了他的麻麻\n给幼小的火柴人取个名字吧！");
                    }
                    JOptionPane.showMessageDialog(frame,"取名成功！把"+Setting.name+"养大吧！");
                    Setting.sex="薯片";
                    Setting.hobby="未知";
                    Setting.age+=1;
                    if (Setting.name.equals("礼拜四")){
                        JOptionPane.showMessageDialog(frame,"礼拜四：嘿嘿嘿我就知道麻麻最喜欢我啦，我来给麻麻跳兔子舞！");
                        Setting.age=-2;
                    }
                    Tools.closeFrame(frame);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
