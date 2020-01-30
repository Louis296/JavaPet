package com.frame;

import com.others.Setting;
import com.others.State;
import com.others.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class ManagerFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel messagePanel;
    private JPanel timePanel;
    private JPanel buttonPanel;

    public void go(){
        if (MainFrame.haveOtherFrame){
            JOptionPane.showMessageDialog(null, Setting.name+"：麻麻我还被挂在鼠标上呢！");
        }
        else if (Setting.haveRemindMessage){
            int res=JOptionPane.showConfirmDialog(null,"麻麻我记不住两件事情哦，好难，要不我把刚刚那件忘了？","提问",JOptionPane.YES_NO_OPTION);
            if (res==0){
                Setting.haveRemindMessage=false;
                this.go();
            }
        }
        else {
            MainFrame.haveOtherFrame=true;
            MainFrame.actionState= State.STOP;
            frame=new JFrame("火柴人助理");
            frame.setBounds(300,400,400,200);
            frame.setResizable(false);

            messagePanel=new JPanel();
            messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            messagePanel.add(new JLabel("要提醒的内容："));

            timePanel=new JPanel();
            timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            timePanel.add(new JLabel("多少分钟之后提醒麻麻？(只能填整数哦)"));

            buttonPanel=new JPanel();
            JButton confirmButton=new JButton("确定");
            JButton cancelButton=new JButton("取消");
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            mainPanel=new JPanel();
            mainPanel.setBorder(new EmptyBorder(5,5,5,5));
            mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
            mainPanel.add(new JLabel("麻麻把事情告诉我，我帮你记着哦！"));
            mainPanel.add(Box.createVerticalStrut(5));
            mainPanel.add(messagePanel);
            JTextField contentInput=new JTextField();
            mainPanel.add(contentInput);
            mainPanel.add(Box.createVerticalStrut(5));
            mainPanel.add(timePanel);
            JTextField timeInput=new JTextField();
            mainPanel.add(timeInput);

            confirmButton.addActionListener(e->{
                if (contentInput.getText()==null||timeInput.getText()==null)
                    JOptionPane.showMessageDialog(frame,"信息没填全哦麻麻！");
                else if (!Tools.isNumeric(timeInput.getText()))
                    JOptionPane.showMessageDialog(frame,"时间填错了哦麻麻！");
                else{
                    Setting.remindMessage=contentInput.getText();
                    Setting.remindTime=Integer.parseInt(timeInput.getText());
                    Calendar calendar=Calendar.getInstance();
                    calendar.add(Calendar.MINUTE,Setting.remindTime);
                    Setting.remindCalendar=calendar;
                    Setting.haveRemindMessage=true;
                    new remindMessageThread().start();
                    JOptionPane.showMessageDialog(frame,"好哦我记住啦！我会在"+Setting.remindTime+"分钟后提醒麻麻！");
                    Tools.closeFrame(frame);
                }
            });

            cancelButton.addActionListener(e->Tools.closeFrame(frame));

            frame.getContentPane().add(mainPanel, BorderLayout.NORTH);
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

    class remindMessageThread extends Thread{

        @Override
        public void run() {
            try{
                while (true){
                    Thread.sleep(60000);
                    Calendar current=Calendar.getInstance();
                    if (current.after(Setting.remindCalendar)){
                        JOptionPane.showMessageDialog(null,"麻麻麻麻，刚刚你让我提醒你\n    "+Setting.remindMessage+"\n记得不要忘记哦！");
                        Setting.haveRemindMessage=false;
                        Setting.remindCalendar=null;
                        Setting.remindMessage="";
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
