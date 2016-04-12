package com.laughpis.translation.gui;

import javax.swing.*;

/**
 * Created by chunya on 2016/4/12.
 */
public class ConfigForm {
    private JTextField textFieldapikey;
    private JLabel labelapikey;
    private JPanel jpanel1;
    private JTextField textFieldkeyfrom;
    private JLabel labelkeyfrom;
    private static ConfigForm configForm = null;

    public static JPanel getConfigForm() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }

        return configForm.jpanel1;
    }
    public static JTextField getTextFeildApiKey() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldapikey;
    }

    public static JTextField getTextFeildKeyFrom() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldkeyfrom;
    }
}
