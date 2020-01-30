package com.action;

import com.others.Tools;

import javax.swing.*;

public class Hungry {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Hungry.png");
        return jLabel;
    }
}
