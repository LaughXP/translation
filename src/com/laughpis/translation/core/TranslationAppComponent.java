package com.laughpis.translation.core;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import com.laughpis.translation.gui.ConfigForm;
import com.laughpis.translation.utils.TranslationConstants;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by chunya on 2016/4/12.
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
        this.apiKey = TranslationConstants.DEFAULT_API_KEY;
        this.keyFrom = TranslationConstants.DEFAULT_KEY_FROM;
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
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
        this.textFeildApiKey = ConfigForm.getTextFeildApiKey();
        this.apiKey = this.textFeildApiKey.getText();
        if(this.apiKey == null || StringUtil.isEmpty(this.apiKey)) {
            this.apiKey = TranslationConstants.DEFAULT_API_KEY;
            this.textFeildApiKey.setText(TranslationConstants.DEFAULT_API_KEY);
        }
        this.textFeildKeyFrom = ConfigForm.getTextFeildKeyFrom();
        this.keyFrom = this.textFeildKeyFrom.getText();
        if(this.keyFrom == null || StringUtil.isEmpty(this.keyFrom)) {
            this.keyFrom = TranslationConstants.DEFAULT_KEY_FROM;
            this.textFeildKeyFrom.setText(TranslationConstants.DEFAULT_KEY_FROM);
        }
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
    }

    @Override
    public void reset() {
        this.textFeildApiKey.setText(this.apiKey);
        this.textFeildKeyFrom.setText(this.keyFrom);
    }

    @Override
    public void disposeUIResources() {
        this.textFeildApiKey.setText(this.apiKey);
        this.textFeildKeyFrom.setText(this.keyFrom);
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getKeyFrom() {
        return this.keyFrom;
    }
}
