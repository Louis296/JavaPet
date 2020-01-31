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
                while (i<24){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Baby/b "+i++ +".png");

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

    public JLabel go(int x,int y){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Baby/b 0.png",x,y);

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Baby/b "+i++ +".png",x,y);

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
