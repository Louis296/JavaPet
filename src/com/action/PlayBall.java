package com.action;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

import javax.swing.*;

public class PlayBall {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/PlayBall/y 0.png");

        new Thread(()->{
            int i=1;
            try{
                while (i<83){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/PlayBall/y "+i++ +".png");

                    if (MainFrame.actionState!= State.PLAYBALL){
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
