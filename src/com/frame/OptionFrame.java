package com.frame;

import javax.swing.*;
import java.awt.*;

public class OptionFrame {
    private JDialog frame;
    private JCheckBox studyCheck;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    public void go(JFrame fatherFrame){
        frame=new JDialog();
        studyCheck=new JCheckBox("火柴人提醒你学习哦");

        contentPanel=new JPanel();
        contentPanel.add(studyCheck);

        buttonPanel=new JPanel();
        JButton confirmButton=new JButton("确认");
        JButton cancelButton=new JButton("取消");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        frame.setBounds(
                new Rectangle(
                    (int) fatherFrame.getBounds().getX()+50,
                    (int) fatherFrame.getBounds().getY()+50,
                    150,100
                )
        );
        frame.setModalityType((Dialog.ModalityType.APPLICATION_MODAL));
        frame.setVisible(true);
    }
}
