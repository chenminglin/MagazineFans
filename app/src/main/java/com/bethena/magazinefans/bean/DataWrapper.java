package com.bethena.magazinefans.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWrapper<T> {
    public String status;
    public String error;
    public List<T> focus;
    @SerializedName(value = "new")
    public List<T> data;
}
