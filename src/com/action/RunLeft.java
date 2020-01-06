package com.action;


import javax.swing.*;
import com.frame.MainFrame;
import com.tool.Tools;

public class RunLeft {
    public JLabel go() {
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel, "Image/RunLeft/0.png");

        Thread runThread=new Thread(() -> {
            int i = 1;
            try {
                while (true) {
                    Thread.sleep(200);
                    Tools.cgJLabelImg(jLabel, "Image/RunLeft/" + i++ + ".png");
                    if (i > 3) i = 0;

                    if ("RunLeft" != MainFrame.actionflag) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        runThread.start();

        return jLabel;
    }


}
