package com.laughpis.translation.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import com.laughpis.translation.utils.HttpClientPool;
import com.laughpis.translation.utils.TranslationConstants;

import java.net.URLEncoder;

/**
 * @author by laugh on 2016/4/12.
 */
public class TranslationAction extends AnAction {

    private JsonParser parser = new JsonParser();

    @Override
    public void actionPerformed(AnActionEvent e) {
        Application application = ApplicationManager.getApplication();
        TranslationAppComponent component = application.getComponent(TranslationAppComponent.class);
        if (component == null) {
            Messages.showDialog("崩溃了！暂无解决方案", "Selected Element:", new String[]{"OK"}, -1, null);
            return;
        }
//        String apiKey = component.getApiKey();
//        String keyFrom = component.getKeyFrom();
        String apiKey = TranslationConstants.DEFAULT_API_KEY_VAL;
        String keyFrom = TranslationConstants.DEFAULT_KEY_FROM_VAL;
        if (checkApiKey(apiKey, keyFrom)) {
            return;
        }

        translate(e, apiKey, keyFrom);

    }

    private void translate(AnActionEvent e, String apiKey, String keyFrom) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        String result;
        if (editor != null) {
            String text = editor.getSelectionModel().getSelectedText();
            if (text != null) try {
                text = StringUtil.replace(text,"*","");
                text = StringUtil.replace(text,"\n","");
                text = StringUtil.replace(text,"<tt>","");
                text = StringUtil.replace(text,"</tt>","");
                text = StringUtil.replace(text,"@link","");
                text = StringUtil.replace(text,"{","");
                text = StringUtil.replace(text,"}","");
                text = URLEncoder.encode(text, "utf-8");
                result = HttpClientPool.getHttpClient().get(TranslationConstants.genUrl(apiKey, keyFrom, text));
                JsonObject resultJson = parser.parse(result).getAsJsonObject();
                String errorCode = resultJson.get("errorCode").toString();
                String translationText = resultJson.get("translation").toString();
                JsonObject basic = resultJson.getAsJsonObject("basic");
                String basicExplainText = null;
                if (basic != null) {
                    basicExplainText = basic.getAsJsonArray("explains").toString();
                }
                JsonArray webTextJsonArray = resultJson.getAsJsonArray("web");
                StringBuilder sb = new StringBuilder();
                if (webTextJsonArray != null) {
                    for (JsonElement jsonElement : webTextJsonArray) {
                        sb.append(jsonElement.getAsJsonObject().get("key"));
                        sb.append(":");
                        sb.append(jsonElement.getAsJsonObject().get("value"));
                        sb.append("\n");
                    }
                }
                result = TranslationConstants.formateResult(translationText, basicExplainText, sb.toString());
            } catch (Exception exception) {
                exception.printStackTrace();
                result = "一定是你的打开方式不对:" + exception.getMessage() + exception.toString();
            }
            else {
                result = "先划词有木有";
            }
            Messages.showDialog(result, "Selected Element:", new String[]{"OK"}, 0, null);
        } else {
            Messages.showDialog("一定是你的打开方式不对！", "Selected Element:", new String[]{"OK"}, 0, null);
        }
    }

    private boolean checkApiKey(String apiKey, String keyFrom) {
        if (apiKey == null || keyFrom == null) {
            String msg = "没有设置翻译api\n" +
                    "勤奋的人早已经去有道api官网申请了\t:\thttp://fanyi.youdao.com/openapi\n" +
                    "以下是懒人专用api,每小时只允许调用1000次:\n" +
                    "apikey\t:\t" + TranslationConstants.DEFAULT_API_KEY_VAL + "\nkeyfrom\t:\t" + TranslationConstants.DEFAULT_KEY_FROM_VAL +
                    "\n请打开setting,搜索translation. 已经帮你填好，你只需要点击apply按钮。\n这个弹出窗的意义在于,去申请自己的api key!";
            Messages.showDialog(msg, "Selected Element:", new String[]{"OK"}, 0, null);
            return true;
        }
        return false;
    }
}
