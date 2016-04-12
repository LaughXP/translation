package com.laughpis.translation.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.laughpis.translation.utils.HttpClientPool;
import com.laughpis.translation.utils.TranslationConstants;

import java.net.URLEncoder;

/**
 * Created by chunya on 2016/4/12.
 */
public class TranslationAction extends AnAction {

    private JsonParser parser = new JsonParser();


    @Override
    public void actionPerformed(AnActionEvent e) {
        Application application = ApplicationManager.getApplication();
        TranslationAppComponent component = application.getComponent(TranslationAppComponent.class);
        if(component == null) {
            Messages.showDialog("崩溃了！暂无解决方案", "Selected Element:", new String[]{"OK"}, -1, null);
            return;
        }
        String apiKey = component.getApiKey();
        String keyFrom = component.getKeyFrom();

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            String text = editor.getSelectionModel().getSelectedText();
            String result;
            if(text != null) {
                try {
                    text = URLEncoder.encode(text,"utf-8");
                    result = HttpClientPool.getHttpClient().get(TranslationConstants.genUrl(apiKey,keyFrom,text));
                    JsonObject resultJson = parser.parse(result).getAsJsonObject();
                    String errorCode = resultJson.get("errorCode").toString();
                    String translationText = resultJson.get("translation").toString();
                    String basicExplainText = resultJson.getAsJsonObject("basic").getAsJsonArray("explains").toString();
                    String webText = resultJson.getAsJsonArray("web").toString();
//                    Map<String,String> resultMap = gson.fromJson(result,new TypeToken<Map<String, String>>() {}.getType());
                    result = TranslationConstants.formateResult(translationText,basicExplainText,webText);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    result = "一定是你的打开方式不对:" + exception.getMessage() + exception.toString();
                }
            } else {
                result = "先划词有木有";
            }
            Messages.showDialog(result, "Selected Element:", new String[]{"OK"}, -1, null);
        } else {
            Messages.showDialog("一定是你的打开方式不对！", "Selected Element:", new String[]{"OK"}, -1, null);
        }

    }
}
