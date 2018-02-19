package com.aviv.yandexapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 17-02-2018.
 */

public class model {

    @SerializedName("code")
    private Integer code;
    @SerializedName("lang")
    private String lang;
    @SerializedName("text")
    private List<String> text = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

}
