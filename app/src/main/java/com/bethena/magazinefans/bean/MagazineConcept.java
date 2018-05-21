package com.bethena.magazinefans.bean;

public class MagazineConcept {
    public String magCover;
    public String magDate;
    public String magId;
    public String magName;

    @Override
    public String toString() {
        return "MagazineConcept{" +
                "magCover='" + magCover + '\'' +
                ", magDate='" + magDate + '\'' +
                ", magId='" + magId + '\'' +
                ", magName='" + magName + '\'' +
                '}';
    }
}
