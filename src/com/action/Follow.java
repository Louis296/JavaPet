package com.action;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

import javax.swing.*;

public class Follow {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Follow/f 0.png");

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(200);
                    Tools.cgJLabelImg(jLabel,"Image/Follow/f "+i++ +".png");
                    if (i>2)
                        i=0;
                    if (MainFrame.actionState!= State.FOLLLOW)
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        return jLabel;
    }

}
