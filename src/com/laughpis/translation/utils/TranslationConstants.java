package com.laughpis.translation.utils;

/**
 * Created by chunya on 2016/4/12.
 */
public class TranslationConstants {
    private final static String url = "http://fanyi.youdao.com/openapi.do?keyfrom=%s&key=%s&type=data&doctype=json&version=1.1&q=%s";
    public final static String DEFAULT_API_KEY_VAL = "1848675162";
    public final static String DEFAULT_KEY_FROM_VAL = "IDEAtranslation";
    public final static String API_KEY = "apikey";
    public final static String KEY_FROM = "keyfrom";

    private final static String resultFormate = "%s\n%s\n%s";

    public static String genUrl(String apiKey,String keyFrom,String text) {
        return String.format(url,keyFrom,apiKey,text);
    }

    public static String formateResult(String translationText,String basicExplainText,String webText) {
        return String.format(resultFormate,translationText,basicExplainText==null?"":basicExplainText,webText==null?"":webText);
    }
}
