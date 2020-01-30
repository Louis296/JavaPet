package com.frame;

import com.others.Setting;

import javax.swing.*;
import java.awt.*;

public class OptionFrame {
    private JDialog frame;
    private JCheckBox onTopCheck;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private Boolean onTop;
    public void go(JFrame fatherFrame){
        frame=new JDialog();
        frame.setTitle("高级选项");
        onTopCheck =new JCheckBox("保持火柴人在所有窗口前");
        onTopCheck.setSelected(Setting.onTop);
        onTop=onTopCheck.isSelected();


        contentPanel=new JPanel();
        contentPanel.add(onTopCheck);
        onTopCheck.addChangeListener(changeEvent -> {
            JCheckBox checkBox=(JCheckBox) changeEvent.getSource();
            onTop = checkBox.isSelected();
        });

        buttonPanel=new JPanel();
        JButton confirmButton=new JButton("确认");
        JButton cancelButton=new JButton("取消");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        confirmButton.addActionListener(e->{
            Setting.onTop=onTop;
            MainFrame.mainFrame.setAlwaysOnTop(Setting.onTop);
            frame.setVisible(false);
        });
        cancelButton.addActionListener(e->frame.setVisible(false));

        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        frame.setBounds(
                new Rectangle(
                    (int) fatherFrame.getBounds().getX()+50,
                    (int) fatherFrame.getBounds().getY()+50,
                    180,100
                )
        );
        frame.setResizable(false);
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setVisible(true);
    }

}
