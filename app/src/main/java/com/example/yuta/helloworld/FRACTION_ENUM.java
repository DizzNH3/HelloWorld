package com.example.yuta.helloworld;

/**
 * Created by Yuta on 2015/04/30.
 */
public class FRACTION_ENUM{
    static public enum FRACTION_OPTION {
    CEIL_FACTION(0, "切り捨て"),
    FLOOR_FRACTION(1, "切り上げ"),
    ROUND_FRACTION(2, "四捨五入");

    private final int id;
    private final String name;

    private FRACTION_OPTION(final int id, final String name) {
        this.name = name;
        this.id = id;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    }

}
