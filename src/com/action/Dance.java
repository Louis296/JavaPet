package com.action;

import javax.swing.*;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

public class Dance {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Dance/dance01.png");

        new Thread(() -> {
            int i=2;
            try{
                while(true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Dance/dance "+i++ +".png");
                    if (i>64){
                        i=0;
                    }
                    if (MainFrame.actionState!= State.DANCE)
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        return jLabel;
    }
}
