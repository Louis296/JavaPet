package com.action;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

import javax.swing.*;

public class Baby {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Baby/b 0.png");

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Baby/b "+i++ +".png");
                    if (i>23) i=0;

                    if (MainFrame.actionState!= State.BABY){
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
