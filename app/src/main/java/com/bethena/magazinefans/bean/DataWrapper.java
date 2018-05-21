package com.bethena.magazinefans.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWrapper<T> {
    public int status;
    public String error;
    @SerializedName(value = "new", alternate = {"focus", "data", "magazine", "content"})
    public List<T> data;
}
