package com.action;

import com.tool.Tools;

import javax.swing.*;

public class Thanks {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Thanks/a 0.png");

        new Thread(()->{
            int i=1;
            try{
                while (true){
                    Thread.sleep(100);
                    Tools.cgJLabelImg(jLabel,"Image/Thanks/a "+i++ +".png");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        return jLabel;
    }
}
