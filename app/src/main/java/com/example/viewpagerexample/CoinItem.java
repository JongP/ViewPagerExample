package com.example.viewpagerexample;

public class CoinItem {
    private String name;
    private String many;

    public CoinItem(String name, String many) {
        this.name = name;
        this.many = many;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMany() {
        return many;
    }

    public void setMany(String many) {
        this.many = many;
    }

}
