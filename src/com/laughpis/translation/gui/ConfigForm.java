package com.laughpis.translation.gui;

import javax.swing.*;

/**
 * @author by laugh on 2016/4/12.
 */
public class ConfigForm {
    private JTextField textFieldapikey;
    private JLabel labelapikey;
    private JPanel jpanel;
    private JTextField textFieldkeyfrom;
    private JLabel labelkeyfrom;
    private static ConfigForm configForm = null;

    public static JPanel getConfigForm() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }

        return configForm.jpanel;
    }
    public static JTextField getTextFieldApiKey() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldapikey;
    }

    public static JTextField getTextFieldKeyFrom() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }
        return configForm.textFieldkeyfrom;
    }
}
