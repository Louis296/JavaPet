package com.frame;

import javax.swing.*;

public class WelcomeFrame {
    public WelcomeFrame(){
        int res= JOptionPane.showConfirmDialog(
                null,
                "Only WXL Could run this Program!",
                "确认身份",JOptionPane.YES_NO_CANCEL_OPTION);

    }

}
