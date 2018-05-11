package com.bethena.magazinefans.bean;

import java.util.List;

public class DataWrapper<T> {
    public String status;
    public String error;
    public List<T> focus;
    public List<T> data;
}
