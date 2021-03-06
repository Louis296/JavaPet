package com.action;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

import javax.swing.*;

public class Ready {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Ready/Ready_ 0.png");

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Ready/Ready_ "+i++ +".png");
                    if (i>14) i=0;

                    if (MainFrame.actionState!= State.READY){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        return jLabel;
    }
}