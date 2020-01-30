package com.frame;

import com.others.Setting;

import javax.swing.*;

public class WelcomeFrame {
    public WelcomeFrame(){
        if (Setting.firstRun){
            JOptionPane.showMessageDialog(null,"谨以此程序，祝我最最可爱的wxl宝宝生日快乐！！！！\n希望这只火柴人能给宝宝带来快乐！玩得愉快！！\n               --来自最爱wxl的zzw的祝福");
        }


    }

}
