package com.laughpis.translation.gui;

import javax.swing.*;

/**
<<<<<<< HEAD
 * @author by laugh on 2016/4/12.
=======
 * Created by chunya on 2016/4/12.
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
 */
public class ConfigForm {
    private JTextField textFieldapikey;
    private JLabel labelapikey;
<<<<<<< HEAD
    private JPanel jpanel;
=======
    private JPanel jpanel1;
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
    private JTextField textFieldkeyfrom;
    private JLabel labelkeyfrom;
    private static ConfigForm configForm = null;

    public static JPanel getConfigForm() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }

<<<<<<< HEAD
        return configForm.jpanel;
    }
    public static JTextField getTextFieldApiKey() {
=======
        return configForm.jpanel1;
    }
    public static JTextField getTextFeildApiKey() {
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldapikey;
    }

<<<<<<< HEAD
    public static JTextField getTextFieldKeyFrom() {
=======
    public static JTextField getTextFeildKeyFrom() {
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldkeyfrom;
    }
}
