package com.laughpis.translation.core;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.laughpis.translation.gui.ConfigForm;
import com.laughpis.translation.utils.TranslationConstants;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
<<<<<<< HEAD
 * @author by laugh on 2016/4/12.
=======
 * Created by chunya on 2016/4/12.
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
 */
public class TranslationAppComponent implements ApplicationComponent,Configurable {
    private JTextField textFeildApiKey;
    private String apiKey;
    private JTextField textFeildKeyFrom;
    private String keyFrom;
    public TranslationAppComponent() {
    }

    @Override
    public void initComponent() {
        this.apiKey = PropertiesComponent.getInstance().getValue(TranslationConstants.API_KEY);
        this.keyFrom = PropertiesComponent.getInstance().getValue(TranslationConstants.KEY_FROM);
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "translation";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "translation";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        PropertiesComponent instance = PropertiesComponent.getInstance();
<<<<<<< HEAD
        this.textFeildApiKey = ConfigForm.getTextFieldApiKey();
        this.apiKey = instance.getValue(TranslationConstants.API_KEY,TranslationConstants.DEFAULT_API_KEY_VAL);
        this.textFeildApiKey.setText(this.apiKey);

        this.textFeildKeyFrom = ConfigForm.getTextFieldKeyFrom();
=======
        this.textFeildApiKey = ConfigForm.getTextFeildApiKey();
        this.apiKey = instance.getValue(TranslationConstants.API_KEY,TranslationConstants.DEFAULT_API_KEY_VAL);
        this.textFeildApiKey.setText(this.apiKey);

        this.textFeildKeyFrom = ConfigForm.getTextFeildKeyFrom();
>>>>>>> 3549c0a7d09753aa9f6bfbf8cffd9e4cc1c5fb5b
        this.keyFrom = instance.getValue(TranslationConstants.KEY_FROM,TranslationConstants.DEFAULT_KEY_FROM_VAL);
        this.textFeildKeyFrom.setText(this.keyFrom);
        return ConfigForm.getConfigForm();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        apiKey = this.textFeildApiKey.getText();
        keyFrom = this.textFeildKeyFrom.getText();
        setConfig();
    }

    @Override
    public void reset() {
        this.textFeildApiKey.setText(this.apiKey);
        this.textFeildKeyFrom.setText(this.keyFrom);
        setConfig();
    }

    @Override
    public void disposeUIResources() {
        this.textFeildApiKey.setText(this.apiKey);
        this.textFeildKeyFrom.setText(this.keyFrom);
        setConfig();
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getKeyFrom() {
        return this.keyFrom;
    }

    private void setConfig() {
        PropertiesComponent.getInstance().setValue(TranslationConstants.API_KEY,this.apiKey);
        PropertiesComponent.getInstance().setValue(TranslationConstants.KEY_FROM,this.keyFrom);
    }
}
