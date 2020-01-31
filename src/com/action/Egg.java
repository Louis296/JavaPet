package com.action;

import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

import javax.swing.*;

public class Egg {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Egg/r 0.png");

        new Thread(()->{
            int i=1;
            int times=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Egg/r "+i++ +".png");
                    if (i>=8) {
                        i=0;
                        times++;
                    }
                    if (MainFrame.actionState!= State.EGG){
                        break;
                    }
                    if (times>=8)
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        return jLabel;
    }

    public JLabel go(int x,int y){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Egg/r 0.png",x,y);

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Egg/r "+i++ +".png",x,y);
                    if (i>8) i=0;

                    if (MainFrame.actionState!= State.EGG){
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
